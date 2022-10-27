package com.nttdata.nova.bookStore.repository;

import org.springframework.stereotype.Repository;
import com.nttdata.nova.bookStore.entity.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository

public interface IBookRepository extends JpaRepository<BookEntity, Long> {
	public List<BookEntity> findBookByTitle(String title);
	public List<BookEntity> findBookByEditorial(EditorialEntity editorial);
}
