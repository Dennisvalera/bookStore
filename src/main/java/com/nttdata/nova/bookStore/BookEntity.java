package com.nttdata.nova.bookStore;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.nttdata.nova.bookStore.EditorialEntity;


@Entity
@Table(name="Books")

public class BookEntity {
	private Long id;
	private String title;
	private String author;
	private Date publish;
	private Integer pages;
	private String description;
    private EditorialEntity editorial;
    
    public BookEntity(int i, String string, String string2, LocalDate now, Integer j, String string3, EditorialEntity object) {

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
	public EditorialEntity getEditorialEntity() {
		return editorial;
	}
	public void setEditorialEntity(EditorialEntity editorial) {
		this.editorial = editorial;
	}
}
