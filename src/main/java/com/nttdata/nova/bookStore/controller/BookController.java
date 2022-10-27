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

import com.nttdata.nova.bookStore.dto.BookDTO;
import com.nttdata.nova.bookStore.dto.EditorialDTO;
import com.nttdata.nova.bookStore.service.impl.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/bookstore")

public class BookController {
	@Autowired
	private BookService bookservice;
	
	@GetMapping("/api/all-books")
	@Operation(summary = "Find all of the existing books")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Books list found & returned", content = @Content),
			@ApiResponse(responseCode = "404", description = "No books found", content = @Content) })
	public ResponseEntity<List<BookDTO>> getAllBooks() {
		return new ResponseEntity<List<BookDTO>>(bookservice.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/api/book/{id}")
	@Operation(summary = "Find a book by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Book found & returned", content = @Content),
			@ApiResponse(responseCode = "404", description = "No book found", content = @Content) })
	public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long id) {
		return new ResponseEntity<BookDTO>(bookservice.findById(id), HttpStatus.OK);
	}
	
	@GetMapping(path = "/api/book/{title}")
	@Operation(summary = "Find a book by title")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Book found & returned", content = @Content),
			@ApiResponse(responseCode = "404", description = "No book found", content = @Content) })
	public ResponseEntity<List<BookDTO>> getBookByTitle(@PathVariable("title") String title) {
		return new ResponseEntity<List<BookDTO>>(bookservice.findByTitle(title), HttpStatus.OK);
	}
	
	@GetMapping("/api/book/editorial")
	@Operation(summary = "Find a book by editorial")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Book found & returned", content = @Content),
			@ApiResponse(responseCode = "404", description = "No book found", content = @Content) })
	public ResponseEntity<List<BookDTO>> getBookByEditorial(@RequestBody EditorialDTO editorial) {
	    return new ResponseEntity<List<BookDTO>>(bookservice.findByEditorial(editorial), HttpStatus.OK);
	}
	
	@PostMapping("/api/book")
	@Operation(summary = "Create a new book & post it into the database")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Books list found & returned",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = BookDTO.class)) })})
	public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookdto){
		BookDTO bookDto = bookservice.save(bookdto);
		return new ResponseEntity<BookDTO>(bookDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/api/update-book")
	@Operation(summary = "Update a book")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Book updated",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = BookDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "No book found", content = @Content) })
	public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookdto){
			return new ResponseEntity<BookDTO>(bookservice.update(bookdto), HttpStatus.OK);
	}
	
	@DeleteMapping("/api/delete-book/{id}")
	@Operation(summary = "Delete a book by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Book deleted", content = @Content),
			@ApiResponse(responseCode = "404", description = "No book found", content = @Content) })
	public ResponseEntity<String> deleteBook(@PathVariable("id") Long id){
		BookDTO bookdto = bookservice.findById(id);
		bookservice.delete(bookdto);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
