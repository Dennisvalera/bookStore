package com.nttdata.nova.bookStore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nttdata.nova.bookStore.dto.BookDTO;

@Entity
@Table(name="books")

public class BookEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
	@Column(name="ID")
	private Long id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="AUTHOR")
	private String author;
	
	@Column(name="PUBLISH")
	private Date publish;
	
	@Column(name="PAGES")
	private Integer pages;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="EDITORIAL", nullable = false)
    private EditorialEntity editorial;
    
    public BookEntity(Long i, String string, String string2, Date Date, Integer j, String string3, EditorialEntity object) {
    	this.id = i;
    	this.title = string;
    	this.author = string2;
    	this.publish = Date;
    	this.pages = j;
    	this.description = string3;
    	this.editorial = object;
	}
    
    public BookEntity(BookDTO bookDto) {
		this.id = bookDto.getId();
		this.title = bookDto.getTitle();
		this.author = bookDto.getAuthor();
		this.publish = bookDto.getPublish();
		this.pages = bookDto.getPages();
		this.description = bookDto.getDescription();
		this.editorial = new EditorialEntity(bookDto.getEditorial());
	}
    
    public BookEntity() {
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
	public EditorialEntity getEditorial() {
		return editorial;
	}
	public void setEditorial(EditorialEntity editorial) {
		this.editorial = editorial;
	}
}
