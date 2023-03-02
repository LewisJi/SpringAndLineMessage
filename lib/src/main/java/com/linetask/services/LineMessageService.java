package com.linetask.services;

import java.util.List;

import com.linetask.document.LineMessage;

public interface LineMessageService {

	public List<LineMessage> findAll();
	public boolean save(LineMessage lineMessage);
	public String getMessageListOfUser();
	public void pushMessages(String message);
	public void replyMessage(String replyToken, String message);
}
