package com.nttdata.nova.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.nova.bookStore.repository.*;
import com.nttdata.nova.bookStore.entity.*;

@Service
public class BookService {
	@Autowired
	IBookRepository iBookRepository;
	
	public boolean BookisEmpty() {
		return iBookRepository.count() == 0;
	}
	
	public List<BookEntity> getAllBooks(){
		return iBookRepository.findAll();
	}
	
	public List<BookEntity> getBook(){
		return iBookRepository.findBookByEditorial(null);
	}
	
	public void save(BookEntity bookentity) {
		iBookRepository.save(bookentity);
	}
	
	public void deleteBookById(Long id) {
		iBookRepository.deleteById(id);
	}
		
	public boolean BookexistsById(Long id) {
		return iBookRepository.existsById(id);
	}
}
	
