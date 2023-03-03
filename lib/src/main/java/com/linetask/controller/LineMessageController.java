package com.linetask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.linecorp.bot.model.event.CallbackRequest;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.linetask.document.LineMessage;
import com.linetask.request.LineMessageRequest;
import com.linetask.services.LineMessageService;

/**
 * 
 * @author LewisJi Sample Line Messages API description: String boot +
 *         linecorp.bot + mongodb , it could send messages to Line and use
 *         mongodb
 */
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@LineMessageHandler
@RestController
public class LineMessageController {

	@Autowired
	LineMessageService lmService;

	@GetMapping("/message")
	public String getMessage(@RequestParam(value = "context", defaultValue = "here data") String context) {
		return String.format("ok %s!", context);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World Lewis") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/findall")
	public Object findAll() {
		return lmService.findAll();
	}

	@PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
	public boolean addOne(LineMessageRequest rq) {

		return lmService.save(new LineMessage(rq.getName(), rq.getMessage(), rq.getType()));
	}

	// push a message to line ,#5 Create an API send message back to line
	@GetMapping(value = "/push/{message}", produces = "application/json;charset=UTF-8")
	public void pushMessage(@PathVariable String message) {
		lmService.pushMessages(message);
	}

	// send message list of user,#6 API query message list of the user from MongoDB
	@PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
	public void userList(CallbackRequest callbackRequest) {
		lmService.pushMessages(lmService.getMessageListOfUser());
	}

	// callback #4 RESTful API with Spring annotation
	@PostMapping(value = "/callback", produces = "application/json;charset=UTF-8")
	@EventMapping
	public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		String replyToken = event.getReplyToken();
		String text = event.getMessage().getText();
		lmService.replyMessage(replyToken, text);

	}

	
}
