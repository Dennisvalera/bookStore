package com.nttdata.nova.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.nova.bookStore.entity.*;
import com.nttdata.nova.bookStore.service.*;
import com.nttdata.nova.bookStore.repository.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/bookstore")

public class EditorialController {
	@Autowired
	private IEditorialRepository iEditorialRepository;
	
	@Autowired
	private EditorialService editorialservice;
	
	@GetMapping("/api/all-editorials")
	@Operation(summary = "Read all of the existing editorials from the data base")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Editorials list found & returned", content = @Content),
			@ApiResponse(responseCode = "404", description = "No editorials found", content = @Content) })
	public ResponseEntity<List<EditorialEntity>> getAllEditorials() {
		if(editorialservice.EditorialisEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(editorialservice.getAllEditorials(), HttpStatus.OK);
	}
	
	@PostMapping("/api/editorial")
	@Operation(summary = "Create a new editorial & save it into the database")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Editorials list found & returned",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = EditorialEntity.class)) })})
	public ResponseEntity<String> createEditorial(@RequestBody EditorialEntity editorialentity){
		editorialservice.save(editorialentity); 
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/api/update-editorial/{id}")
	@Operation(summary = "Update a editorial status by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Editorial status changed",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = EditorialEntity.class)) }),
			@ApiResponse(responseCode = "404", description = "No editorials found", content = @Content) })
	public ResponseEntity<String> updateEditorial(@PathVariable("id") long id){
		if(editorialservice.EditorialexistsById(id)) {
			((EditorialEntity) iEditorialRepository).setId(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/api/delete-editorial/{id}")
	@Operation(summary = "Delete a editorial by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Editorial deleted", content = @Content),
			@ApiResponse(responseCode = "404", description = "No editorial found", content = @Content) })
	public ResponseEntity<String> deleteBook(@PathVariable("id") long id){
		if(editorialservice.EditorialexistsById(id)) {
			editorialservice.deleteEditorialById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
