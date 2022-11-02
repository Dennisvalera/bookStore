package com.nttdata.nova.bookStore.dto;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nttdata.nova.bookStore.entity.EditorialEntity;

public class EditorialDTO extends RepresentationModel<EditorialDTO>{
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	

	public EditorialDTO() {
	}
	
	public EditorialDTO(EditorialEntity editorial) {
		this.id = editorial.getId();
		this.name = editorial.getName();
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

}