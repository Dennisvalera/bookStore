package com.nttdata.nova.bookStore.dto;

import java.util.Date;

import com.nttdata.nova.bookStore.collections.BookRegistryEntity;

public class BookRegistryDTO {
	
	private String id;
	private String message;
	private Date date;
	
	public BookRegistryDTO(String string, Date date2) {
		this.message = string;
		this.date = date2;
	}
	
	public BookRegistryDTO(BookRegistryEntity bookregistryEntity) {
		this.id = bookregistryEntity.getId();
		this.message = bookregistryEntity.getMessage();
		this.date = bookregistryEntity.getDate();
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
