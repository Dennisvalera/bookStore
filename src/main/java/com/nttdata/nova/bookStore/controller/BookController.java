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

import com.nttdata.nova.bookStore.entity.BookEntity;
import com.nttdata.nova.bookStore.service.BookService;
import com.nttdata.nova.bookStore.repository.IBookRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/bookstore")

public class BookController {
	@Autowired
	private IBookRepository iBookRepository;
	
	@Autowired
	private BookService bookservice;
	
	@GetMapping("/api/all-books")
	@Operation(summary = "Get all of the existing books from the data base")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Books list found & returned", content = @Content),
			@ApiResponse(responseCode = "404", description = "No books found", content = @Content) })
	public ResponseEntity<List<BookEntity>> getAllBooks() {
		if(bookservice.BookisEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	    return new ResponseEntity<>(bookservice.getAllBooks(), HttpStatus.OK);
	}
	
	@GetMapping("/api/book/editorial")
	@Operation(summary = "Get a book through an editorial from the data base")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Book found & returned", content = @Content),
			@ApiResponse(responseCode = "404", description = "No book found", content = @Content) })
	public ResponseEntity<List<BookEntity>> getBook() {
		if(bookservice.BookisEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	    return new ResponseEntity<>(bookservice.getBook(), HttpStatus.OK);
	}
	
	@PostMapping("/api/book")
	@Operation(summary = "Create a new book & save it into the database")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Books list found & returned",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = BookEntity.class)) })})
	public ResponseEntity<String> createBook(@RequestBody BookEntity bookentity){
		bookservice.save(bookentity);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/api/update-book/{id}")
	@Operation(summary = "Modify a book status by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Book status changed",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = BookEntity.class)) }),
			@ApiResponse(responseCode = "404", description = "No book found", content = @Content) })
	public ResponseEntity<String> updateBook(@PathVariable("id") long id){
		if(bookservice.BookexistsById(id)) {
			((BookEntity) iBookRepository).setId(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/api/delete-book/{id}")
	@Operation(summary = "Delete a book by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Book deleted", content = @Content),
			@ApiResponse(responseCode = "404", description = "No book found", content = @Content) })
	public ResponseEntity<String> deleteBook(@PathVariable("id") long id){
		if(bookservice.BookexistsById(id)) {
			bookservice.deleteBookById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
