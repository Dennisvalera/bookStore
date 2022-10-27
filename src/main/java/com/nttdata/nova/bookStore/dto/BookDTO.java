package com.nttdata.nova.bookStore.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nttdata.nova.bookStore.entity.*;

public class BookDTO{

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("titulo")
	private String title;

	@JsonProperty("autor")
	private String author;
	
	@JsonProperty("publicado")
	private LocalDate publish;

	@JsonProperty("paginas")
	private Integer pages;
	
	@JsonProperty("descripcion")
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
		this.editorial = new EditorialDTO(bookentity.getEditorialEntity());
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
	public LocalDate getPublish() {
		return publish;
	}
	public void setPublish(LocalDate publish) {
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