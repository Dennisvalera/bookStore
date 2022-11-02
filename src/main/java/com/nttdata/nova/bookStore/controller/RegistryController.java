package com.nttdata.nova.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.nova.bookStore.service.IBookRegistryService;

import io.swagger.v3.oas.annotations.Operation;

import com.nttdata.nova.bookStore.dto.BookRegistryDTO;

@RestController
@RequestMapping(value = "/registry")

public class RegistryController {
	@Autowired
	private IBookRegistryService iBookregistryservice;
	
	@GetMapping("/get-book-registry")
	@Operation(summary = "Get book registry", description = "Getting book registry method")
	public ResponseEntity<List<BookRegistryDTO>> getAllBooksRegistries(){
		return new ResponseEntity<List<BookRegistryDTO>>(iBookregistryservice.findAll(), HttpStatus.OK);
	}
}