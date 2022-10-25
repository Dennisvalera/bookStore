package com.nttdata.nova.bookStore;

import org.springframework.stereotype.Repository;
import com.nttdata.nova.bookStore.EditorialEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository

public interface IEditorialRepository extends JpaRepository<EditorialEntity, Long> {
	List<EditorialEntity> findByName(String name);
}
