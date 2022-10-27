package com.nttdata.nova.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/bookstore")

public class EditorialController {
	@Autowired
	private EditorialService editorialservice;
	
	@GetMapping("/api/all-editorials")
	@Operation(summary = "Find all of the existing editorials")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Editorials list found & returned", content = @Content),
			@ApiResponse(responseCode = "404", description = "No editorials found", content = @Content) })
	public ResponseEntity<List<EditorialDTO>> getAllEditorials() {
		return new ResponseEntity<List<EditorialDTO>>(editorialservice.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/api/editorial/{id}")
	@Operation(summary = "Find an editorial by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Editorials list found & returned", content = @Content),
			@ApiResponse(responseCode = "404", description = "No editorials found", content = @Content) })
	public ResponseEntity<EditorialDTO> getEditorialById(@PathVariable("id") Long id){
		return new ResponseEntity<EditorialDTO>(editorialservice.findById(id), HttpStatus.OK);
	}
	
	@GetMapping(path = "/api/editorial/{name}")
	@Operation(summary = "Find an editorial by name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Editorials list found & returned", content = @Content),
			@ApiResponse(responseCode = "404", description = "No editorials found", content = @Content) })
	public ResponseEntity<List<EditorialDTO>> getEditorialByName(@PathVariable("name") String name){
		return new ResponseEntity<List<EditorialDTO>>(editorialservice.findByName(name), HttpStatus.OK);
	}
	
	@PostMapping("/api/editorial")
	@Operation(summary = "Create an editorial & post it into the database")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Editorials list found & returned",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = EditorialDTO.class)) })})
	public ResponseEntity<EditorialDTO> createEditorial(@RequestBody EditorialDTO editorial){
		EditorialDTO editorialDto = editorialservice.save(editorial);
		return new ResponseEntity<EditorialDTO>(editorialDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/api/update-editorial")
	@Operation(summary = "Update an editorial by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Editorial id updated",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = EditorialDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "No editorials found", content = @Content) })
	public ResponseEntity<EditorialDTO> updateEditorial(@RequestBody EditorialDTO editorial){
		return new ResponseEntity<EditorialDTO>(editorialservice.update(editorial), HttpStatus.OK);
	}
	
	@DeleteMapping("/api/delete-editorial/{id}")
	@Operation(summary = "Delete an editorial by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Editorial deleted", content = @Content),
			@ApiResponse(responseCode = "404", description = "No editorial found", content = @Content) })
	public ResponseEntity<String> deleteBook(@PathVariable("id") Long id){
		EditorialDTO editorial = editorialservice.findById(id);
		editorialservice.delete(editorial);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
