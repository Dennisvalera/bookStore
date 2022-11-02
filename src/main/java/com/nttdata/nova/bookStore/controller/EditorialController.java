package com.nttdata.nova.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.nova.bookStore.dto.EditorialDTO;
import com.nttdata.nova.bookStore.service.impl.EditorialService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/editorial")

public class EditorialController {
	@Autowired
	private EditorialService editorialservice;
	
	@PostMapping("/create")
	@Operation(summary = "Add editorial", description = "Adding editorial method")
	public ResponseEntity<EditorialDTO> createEditorial(@RequestBody EditorialDTO editorial){
		EditorialDTO editorialDto = editorialservice.save(editorial);
				
		if(editorialDto!=null) {
			EditorialController.generateEditorialLinks(editorialDto);
		}
		return new ResponseEntity<EditorialDTO>(editorialDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/get")
	@Operation(summary = "Get all editorials", description = "Getting all editorials method")
	public ResponseEntity<List<EditorialDTO>> getAllEditorials() {
		List<EditorialDTO> editorialDtoList = editorialservice.findAll();
		editorialDtoList.forEach(e -> EditorialController.generateEditorialLinks(e));
		return new ResponseEntity<List<EditorialDTO>>(editorialDtoList, HttpStatus.OK);
	}
	
	@GetMapping("/get/id/{id}")
	@Operation(summary = "Find editorial by id", description = "Finding editorial by id method")
	public ResponseEntity<EditorialDTO> getEditorialById(@PathVariable("id") Long id){
		EditorialDTO editorialDto = editorialservice.findById(id);
		if(editorialDto!=null) {
			EditorialController.generateEditorialLinks(editorialDto);
		}
	
		return new ResponseEntity<EditorialDTO>(editorialDto, HttpStatus.OK);
	}
	
	@GetMapping("/get/name/{name}")
	@Operation(summary = "Find editorial by name", description = "Finding editorial by name method")
	public ResponseEntity<List<EditorialDTO>> getEditorialByName(@PathVariable("name") String name){
		List<EditorialDTO> editorialDtoList = editorialservice.findByName(name);
		editorialDtoList.forEach(e -> {
			if(e!=null) {
				EditorialController.generateEditorialLinks(e);
			}
		});
		
		return new ResponseEntity<List<EditorialDTO>>(editorialDtoList, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	@Operation(summary = "Update editorial", description = "Updating editorial method")
	public ResponseEntity<EditorialDTO> updateEditorial(@RequestBody EditorialDTO editorial){
		EditorialDTO editorialDto = editorialservice.save(editorial);
		if(editorialDto!=null) {
			EditorialController.generateEditorialLinks(editorialDto);
		}
		return new ResponseEntity<EditorialDTO>(editorialservice.update(editorial), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/id/{id}")
	@Operation(summary = "Delete editorial by id", description = "Deleting editorial by id method")
	public ResponseEntity<String> deleteBook(@PathVariable("id") Long id){
		EditorialDTO editorial = editorialservice.findById(id);
		editorialservice.delete(editorial);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	public static void generateEditorialLinks(EditorialDTO editorialDto) {
		editorialDto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EditorialController.class).getEditorialById(editorialDto.getId())).withSelfRel());
	}
}