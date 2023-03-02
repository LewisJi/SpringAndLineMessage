package com.linetask.dao;

import java.util.List;

import com.linetask.document.LineMessage;

public interface LineMessageDao {

	// #3 Create objects for MongoDB/collection connection, create model/DTO objects
	// to save/query user message to MongoDB
	
	public List<LineMessage> findAll();

	public boolean saveOne(LineMessage msg);

	public String findByName();
}
