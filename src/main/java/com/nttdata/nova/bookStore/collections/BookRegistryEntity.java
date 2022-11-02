package com.nttdata.nova.bookStore.collections;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.nttdata.nova.bookStore.dto.BookRegistryDTO;

@Entity
@Document("BookRegistryEntity")

public class BookRegistryEntity {
	@Id
	private String id;
	private String message;
	private Date date;
	
	public BookRegistryEntity(String i, String string, Date now) {
		this.id = i;
		this.message = string;
		this.date = now;
	}
	
	public BookRegistryEntity(BookRegistryDTO bookregistryDto) {
		this.id = bookregistryDto.getId();
		this.message = bookregistryDto.getMessage();
		this.date = bookregistryDto.getDate();
	}
	
	public BookRegistryEntity() {
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
