package com.nttdata.nova.bookStore;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Editorials")

public class EditorialEntity {
	private Long id;
	private String name;
	private Set<BookEntity> bookList;
	
	public EditorialEntity(int i, String string, Set<BookEntity> of) {

	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<BookEntity> getBookList() {
		return bookList;
	}
	public void setBookList(Set<BookEntity> bookList) {
		this.bookList = bookList;
	}
}
