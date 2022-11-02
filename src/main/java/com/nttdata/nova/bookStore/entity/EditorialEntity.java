package com.nttdata.nova.bookStore.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nttdata.nova.bookStore.dto.EditorialDTO;

@Entity
@Table(name="editorials")

public class EditorialEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "editorial_seq")
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "editorial")
	private Set<BookEntity> booklist;
	
	public EditorialEntity(Long i, String string, Set<BookEntity> object) {
		this.id = i;
		this.name = string;
		this.booklist = object;
	}
	
	public EditorialEntity(EditorialDTO editorialDto) {
		this.id = editorialDto.getId();
		this.name = editorialDto.getName();
	}
	
	public EditorialEntity() {
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
		return booklist;
	}
	public Set<BookEntity> setBookList(Set<BookEntity> booklist) {
		return booklist;
	}
}
