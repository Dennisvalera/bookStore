package com.nttdata.nova.bookStore.repository;

import org.springframework.stereotype.Repository;
import com.nttdata.nova.bookStore.entity.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository

public interface IEditorialRepository extends JpaRepository<EditorialEntity, Long> {
	public List<EditorialEntity> findByName(String name);
}
