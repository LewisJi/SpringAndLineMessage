package com.linetask.repos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.linetask.document.LineMessage;

@Repository
public interface LineUserMessageRepository extends MongoRepository<LineMessage,String>{
	//TODO
	List<LineMessage> findAll();
}
