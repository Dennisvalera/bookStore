package com.nttdata.nova.bookStore.dto;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nttdata.nova.bookStore.entity.BookEntity;

public class BookDTO extends RepresentationModel<BookDTO>{

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("title")
	private String title;

	@JsonProperty("author")
	private String author;
	
	@JsonProperty("date")
	private Date publish;

	@JsonProperty("pages")
	private Integer pages;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("editorial")
	private EditorialDTO editorial;
	
	public BookDTO() {
	}
	
	public BookDTO(BookEntity bookentity) {
		this.id = bookentity.getId();
		this.title = bookentity.getTitle();
		this.author = bookentity.getAuthor();
		this.publish = bookentity.getPublish();
		this.pages = bookentity.getPages();
		this.description = bookentity.getDescription();
		this.editorial = new EditorialDTO(bookentity.getEditorial());
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPublish() {
		return publish;
	}
	public void setPublish(Date publish) {
		this.publish = publish;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EditorialDTO getEditorial() {
		return editorial;
	}

	public void setEditorial(EditorialDTO editorial) {
		this.editorial = editorial;
	}
}