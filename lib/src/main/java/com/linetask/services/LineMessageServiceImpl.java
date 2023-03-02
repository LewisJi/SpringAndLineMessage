package com.linetask.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linetask.dao.LineMessageDao;
import com.linetask.document.LineMessage;

@Service
public class LineMessageServiceImpl implements LineMessageService{

	@Autowired
	LineMessageDao lmDao;
	
	@Value("${line.bot.channel-token}")
	private String channelToken;
	
	@Value("${line.userid}")
	private String userId;

	@Override
	public List<LineMessage> findAll() {
		System.out.println("LineMessageService findAll");

		return lmDao.findAll();
	}

	@Override
	public boolean save(LineMessage lineMessage) {
		System.out.println("LineMessageService save");

		return lmDao.saveOne(lineMessage);
	}

	@Override
	public String getMessageListOfUser() {
		System.out.println("LineMessageService getMessageListOfUser");
		
		return lmDao.findByName();
	}

	@Override
	public void pushMessages(String message) {
		System.out.println("LineMessageService pushMessages");
		LineMessagingClient client = LineMessagingClient.builder(channelToken).build();
		TextMessage textMessage = new TextMessage(message);
		PushMessage pushMessage = new PushMessage(userId, textMessage);
		client.pushMessage(pushMessage);		
	}

	@Override
	public void replyMessage(String replyToken, String message) {

		LineMessagingClient client = LineMessagingClient.builder(channelToken).build();
		TextMessage textMessage = new TextMessage("你输入的是：" + message);
		ReplyMessage replyMessage = new ReplyMessage(replyToken, textMessage);
		client.replyMessage(replyMessage);
		
		//data cmd query MessageListOfUser
		if(message.equalsIgnoreCase("Data")) {
			this.pushMessages(this.getMessageListOfUser());
		}
	}
	
	
	
}
