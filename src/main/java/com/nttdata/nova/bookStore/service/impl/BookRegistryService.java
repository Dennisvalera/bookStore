package com.nttdata.nova.bookStore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.nova.bookStore.collections.BookRegistryEntity;
import com.nttdata.nova.bookStore.dto.BookRegistryDTO;
import com.nttdata.nova.bookStore.collections.IBookRegistryRepository;
import com.nttdata.nova.bookStore.service.IBookRegistryService;

@Service
public class BookRegistryService implements IBookRegistryService{
	
	@Autowired
	private IBookRegistryRepository iBookRegistryRepository;

	@Override
	public BookRegistryDTO save(BookRegistryDTO bookRegistryDto) {
		
		bookRegistryDto.setId("REGISTRY-" + String.valueOf(iBookRegistryRepository.findAll().size() + 1));
		
		return new BookRegistryDTO(iBookRegistryRepository.save(new BookRegistryEntity(bookRegistryDto)));
	}

	@Override
	public List<BookRegistryDTO> findAll() {
		List<BookRegistryDTO> bookRegistryDtoList = new ArrayList<BookRegistryDTO>();
		
		List<BookRegistryEntity> bookRegistryList = (List<BookRegistryEntity>)iBookRegistryRepository.findAll();
		bookRegistryList.forEach(m -> bookRegistryDtoList.add(new BookRegistryDTO(m)));
		
		return bookRegistryDtoList;
	}

}