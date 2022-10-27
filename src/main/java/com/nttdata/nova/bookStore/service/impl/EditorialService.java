package com.nttdata.nova.bookStore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.nova.bookStore.dto.EditorialDTO;
import com.nttdata.nova.bookStore.entity.EditorialEntity;
import com.nttdata.nova.bookStore.repository.IEditorialRepository;
import com.nttdata.nova.bookStore.service.IEditorialService;

@Service
public class EditorialService implements IEditorialService {

	@Autowired
	private IEditorialRepository iEditorialRepository;

	@Override
	public EditorialDTO save(EditorialDTO editorialDto) {
		editorialDto.setId(null);
		return new EditorialDTO(iEditorialRepository.save(new EditorialEntity(editorialDto)));
	}

	@Override
	public EditorialDTO update(EditorialDTO editorialDto) {
		return new EditorialDTO(iEditorialRepository.save(new EditorialEntity(editorialDto)));
	}

	@Override
	public void delete(EditorialDTO editorialDto) {
		iEditorialRepository.delete(new EditorialEntity(editorialDto));
	}

	@Override
	public EditorialDTO findById(Long id) {
		Optional<EditorialEntity> editorial = iEditorialRepository.findById(id);
		return editorial.isPresent() ? new EditorialDTO(editorial.get()) : null;
	}

	@Override
	public List<EditorialDTO> findAll() {
		List<EditorialDTO> editorialDtoList = new ArrayList<EditorialDTO>();
		
		List<EditorialEntity> editorialList =  (List<EditorialEntity>) iEditorialRepository.findAll();
		editorialList.stream().forEach(e -> editorialDtoList.add(new EditorialDTO(e)));
		
		return editorialDtoList;
	}

	@Override
	public List<EditorialDTO> findByName(String name) {
		List<EditorialDTO> editorialDtoList = new ArrayList<EditorialDTO>();
		
		List<EditorialEntity> editorialList =  (List<EditorialEntity>) iEditorialRepository.findByName(name);
		editorialList.stream().forEach(e -> editorialDtoList.add(new EditorialDTO(e)));
		
		return editorialDtoList;
	}
}