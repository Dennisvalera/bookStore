package com.nttdata.nova.bookStore.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="books")


public class BookEntity {
	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="AUTHOR")
	private String author;
	
	@Column(name="PUBLISH")
	private LocalDate publish;
	
	@Column(name="PAGES")
	private Integer pages;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="EDITORIAL", nullable = false)
    private EditorialEntity editorial;
    
    public BookEntity(Long i, String string, String string2, LocalDate now, Integer j, String string3, EditorialEntity object) {
    	this.id = i;
    	this.title = string;
    	this.author = string2;
    	this.publish = now;
    	this.pages = j;
    	this.description = string3;
    	this.editorial = object;
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
	public EditorialEntity getEditorialEntity() {
		return editorial;
	}
	public void setEditorialEntity(EditorialEntity editorial) {
		this.editorial = editorial;
	}
}
