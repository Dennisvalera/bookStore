package com.nttdata.nova.bookStore.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
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
import com.nttdata.nova.bookStore.exception.InvalidDateException;
import com.nttdata.nova.bookStore.exception.InvalidEditorialException;
import com.nttdata.nova.bookStore.exception.InvalidIdException;
import com.nttdata.nova.bookStore.service.IBookService;
import com.nttdata.nova.bookStore.service.IEditorialService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private IBookService iBookservice;
	
	@Autowired
	private IEditorialService iEditorialservice;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@PostMapping("/create")
	@Operation(summary = "Add book", description = "Adding book method")
	public HttpEntity<BookDTO> createBook(@RequestBody BookDTO book) {
		if(book.getId()!=0) {
			throw new InvalidIdException(book.getId());
		}
		
		if(book.getPublish().after(Calendar.getInstance().getTime())) {
			throw new InvalidDateException();
		}
		
		if(iEditorialservice.findById(book.getEditorial().getId())==null) {
			throw new InvalidEditorialException();
		}
		
		BookDTO bookDto = iBookservice.save(book);

		if(bookDto!=null) {
			BookController.generateBookLinks(bookDto);
		}
		
		if(bookDto!=null && bookDto.getEditorial()!=null) {
			EditorialController.generateEditorialLinks(bookDto.getEditorial());
		}
				
		kafkaTemplate.send("registry", "Book added");

		return new ResponseEntity<BookDTO>(bookDto, HttpStatus.OK);
	}

	@PutMapping("/update")
	@Operation(summary = "Update book", description = "Updating book method")
	public HttpEntity<BookDTO> updateBook(@RequestBody BookDTO book) {
		if(book.getId()==0) {
			throw new  InvalidIdException(book.getId());
		}
		
		if(book.getPublish().after(Calendar.getInstance().getTime())) {
			throw new InvalidDateException();
		}
		
		if(iEditorialservice.findById(book.getEditorial().getId())==null) {
			throw new InvalidEditorialException();
		}
		
		BookDTO bookDto = iBookservice.update(book);

		if(bookDto!=null) {
			BookController.generateBookLinks(bookDto);
		}
		
		if(bookDto!=null && bookDto.getEditorial()!=null) {
			EditorialController.generateEditorialLinks(bookDto.getEditorial());
		}
		
		kafkaTemplate.send("registry", "Book updated");

		return new ResponseEntity<BookDTO>(bookDto, HttpStatus.OK);
	}

	@GetMapping("/get")
	@Operation(summary = "Get all books", description = "Getting all books method")
	public HttpEntity<List<BookDTO>> getAllBooks() {
		List<BookDTO> bookDtoList = iBookservice.findAll();
		bookDtoList.forEach(b -> {
			if(b!=null) {
				BookController.generateBookLinks(b);
			}
			
			if(b!=null && b.getEditorial()!=null) {
				EditorialController.generateEditorialLinks(b.getEditorial());
			}
		});
		
		kafkaTemplate.send("registry", "All books gotten");

		return new ResponseEntity<List<BookDTO>>(bookDtoList, HttpStatus.OK);
	}

	@GetMapping("/get/id/{id}")
	@Operation(summary = "Find a book by id", description = "Finding a book by id method")
	public HttpEntity<BookDTO> getBookById(@PathVariable("id") Long id) {
		BookDTO bookDto = iBookservice.findById(id);

		if(bookDto!=null) {
			BookController.generateBookLinks(bookDto);
		}
		
		if(bookDto!=null && bookDto.getEditorial()!=null) {
			EditorialController.generateEditorialLinks(bookDto.getEditorial());
		}
		
		kafkaTemplate.send("registry", "Book finded by id");

		return new ResponseEntity<BookDTO>(bookDto, HttpStatus.OK);
	}

	@PostMapping("/get/editorial")
	@Operation(summary = "Find a book by editorial", description = "Finding a book by editorial method")
	public HttpEntity<List<BookDTO>> getBooksByEditorial(@RequestBody EditorialDTO editorial) {
		List<BookDTO> bookDtoList = iBookservice.findByEditorial(editorial);
		bookDtoList.forEach(b -> {
			if(b!=null) {
				BookController.generateBookLinks(b);
			}
			
			if(b!=null && b.getEditorial()!=null) {
				EditorialController.generateEditorialLinks(b.getEditorial());
			}
		});
		
		kafkaTemplate.send("registry", "Book finded by editorial");

		return new ResponseEntity<List<BookDTO>>(bookDtoList, HttpStatus.OK);
	}

	@GetMapping("/get/title/{title}")
	@Operation(summary = "Find a book by title", description = "Finding a book by title method")
	public HttpEntity<List<BookDTO>> getBooksByTitle(@PathVariable("title") String title) {
		List<BookDTO> bookDtoList = iBookservice.findByTitle(title);
		bookDtoList.forEach(b -> {
			if(b!=null) {
				BookController.generateBookLinks(b);
			}
			
			if(b!=null && b.getEditorial()!=null) {
				EditorialController.generateEditorialLinks(b.getEditorial());
			}
		});
		
		kafkaTemplate.send("registry", "Book finded by title");

		return new ResponseEntity<List<BookDTO>>(bookDtoList, HttpStatus.OK);
	}

	@DeleteMapping("/delete/id/{id}")
	@Operation(summary = "Delete book", description = "Deleting book method")
	public HttpEntity<String> deleteBook(@PathVariable("id") Long id) {
		BookDTO book = iBookservice.findById(id);
		iBookservice.delete(book);
		
		kafkaTemplate.send("registry", "Book deleted");


		return new ResponseEntity<String>(HttpStatus.OK);
	}

	public static void generateBookLinks(BookDTO bookDto) {
		bookDto.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(bookDto.getId())).withSelfRel());
		bookDto.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(EditorialController.class).getEditorialById(bookDto.getEditorial().getId()))
				.withRel("editorial"));
	}
}
