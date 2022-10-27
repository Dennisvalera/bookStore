package com.nttdata.nova.bookStore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.nova.bookStore.repository.IBookRepository;
import com.nttdata.nova.bookStore.service.IBookService;
import com.nttdata.nova.bookStore.dto.BookDTO;
import com.nttdata.nova.bookStore.dto.EditorialDTO;
import com.nttdata.nova.bookStore.entity.BookEntity;
import com.nttdata.nova.bookStore.entity.EditorialEntity;

@Service
public class BookService implements IBookService{
	@Autowired
	IBookRepository iBookRepository;
	
	@Override
	public BookDTO save(BookDTO bookDto) {
		bookDto.setId(null);
		return new BookDTO(iBookRepository.save(new BookEntity(bookDto)));
	}

	@Override
	public BookDTO update(BookDTO bookDto) {	
		return new BookDTO(iBookRepository.save(new BookEntity(bookDto)));	
	}

	@Override
	public void delete(BookDTO bookDto) {
		iBookRepository.delete(new BookEntity(bookDto));
	}

	@Override
	public BookDTO findById(Long id) {
		Optional<BookEntity> book = iBookRepository.findById(id);			
		return book.isPresent() ? new BookDTO(book.get()) : null;
	}

	@Override
	public List<BookDTO> findAll() {
		List<BookDTO> bookDtoList = new ArrayList<BookDTO>();
		
		List<BookEntity> bookList =  (List<BookEntity>) iBookRepository.findAll();
		bookList.stream().forEach(b -> bookDtoList.add(new BookDTO(b)));
		
		return bookDtoList;
	}

	@Override
	public List<BookDTO> findByTitle(String title) {
		List<BookDTO> bookDtoList = new ArrayList<BookDTO>();
		
		List<BookEntity> bookList =  (List<BookEntity>) iBookRepository.findBookByTitle(title);
		bookList.stream().forEach(b -> bookDtoList.add(new BookDTO(b)));
		
		return bookDtoList;
	}

	@Override
	public List<BookDTO> findByEditorial(EditorialDTO editorialDto) {
		List<BookDTO> bookDtoList = new ArrayList<BookDTO>();
		
		List<BookEntity> bookList =  (List<BookEntity>) iBookRepository.findBookByEditorial(new EditorialEntity(editorialDto));
		bookList.stream().forEach(b -> bookDtoList.add(new BookDTO(b)));
		
		return bookDtoList;
	}
	
}