package com.nttdata.nova.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.nova.bookStore.entity.*;
import com.nttdata.nova.bookStore.repository.*;

@Service
public class EditorialService {
	@Autowired
	IEditorialRepository iEditorialRepository;
	
	public boolean EditorialisEmpty() {
		return iEditorialRepository.count() == 0;
	}
			
	public List<EditorialEntity> getAllEditorials(){
		return iEditorialRepository.findAll();
	}
		
	public void save(EditorialEntity editorialentity) {
		iEditorialRepository.save(editorialentity);
	}
			
	public void deleteEditorialById(Long id) {
		iEditorialRepository.deleteById(id);
	}
			
	public boolean EditorialexistsById(Long id) {
		return iEditorialRepository.existsById(id);
	}
}
