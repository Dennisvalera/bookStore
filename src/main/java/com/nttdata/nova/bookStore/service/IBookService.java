package com.nttdata.nova.bookStore.service;

import java.util.List;

import com.nttdata.nova.bookStore.dto.BookDTO;
import com.nttdata.nova.bookStore.dto.EditorialDTO;

public interface IBookService {
	public BookDTO save(BookDTO bookentity);
	public BookDTO update(BookDTO bookentity);
	public void delete(BookDTO bookentity);
	public BookDTO findById(Long id);
	public List<BookDTO> findAll();
	public List<BookDTO> findByTitle(String title);
	public List<BookDTO> findByEditorial(EditorialDTO editorial);
}
