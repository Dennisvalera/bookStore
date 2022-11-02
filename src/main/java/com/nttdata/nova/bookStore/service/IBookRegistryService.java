package com.nttdata.nova.bookStore.service;

import java.util.List;

import com.nttdata.nova.bookStore.dto.BookRegistryDTO;

public interface IBookRegistryService {

	public BookRegistryDTO save (BookRegistryDTO bookRegistryDto);
	public List<BookRegistryDTO> findAll ();
}