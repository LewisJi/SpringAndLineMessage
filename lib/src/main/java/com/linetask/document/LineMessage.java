package com.linetask.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "lineUserMessage")
public class LineMessage {

	@Id
	private String id;
	
	@Field("name")
	private String name;
	
	@Field("message")
	private String message;
	
	@Field("type")
	private String type;

	public LineMessage(String name, String message, String type) {
		super();
		this.name = name;
		this.message = message;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
