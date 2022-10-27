package com.nttdata.nova.bookStore.service;

import java.util.List;

import com.nttdata.nova.bookStore.dto.EditorialDTO;

public interface IEditorialService {
	public EditorialDTO save(EditorialDTO editorial);
	public EditorialDTO update(EditorialDTO editorial);
	public void delete(EditorialDTO editorial);
	public EditorialDTO findById(Long id);
	public List<EditorialDTO> findAll();
	public List<EditorialDTO> findByName(String name);
}
