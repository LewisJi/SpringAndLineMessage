package com.linetask.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.linetask.document.LineMessage;

@Repository
public class LineMessageDaoImp implements LineMessageDao{

	@Autowired
	MongoTemplate mt;
	
	@Override
	public List<LineMessage> findAll() {
		List<LineMessage> lm = mt.findAll(LineMessage.class);
		for (LineMessage lineMessage : lm) {
			System.out.print(lineMessage.getMessage());
		}
		
		return lm;
	}

	@Override
	public boolean saveOne(LineMessage msg) {
		LineMessage lm =mt.save(msg);
		
		return lm==null?false:true;
	}

	@Override
	public String findByName() {
		//here fix user tester
		List<LineMessage> listmgs= mt.find(Query.query(Criteria.where("name").is("tester")), LineMessage.class);
		StringBuilder sb = new StringBuilder();
		for (LineMessage lineMessage : listmgs) {
		    sb.append(String.format("Name: %s, Message: %s \n", lineMessage.getName(), lineMessage.getMessage()));
		}
		return sb.toString();

		
	}

}
