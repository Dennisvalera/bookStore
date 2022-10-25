package com.nttdata.nova.bookStore;

import org.springframework.stereotype.Repository;
import com.nttdata.nova.bookStore.BookEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository

public interface IBookRepository extends JpaRepository<BookEntity, Long> {
	List<BookEntity> findBookByTitle(String title);
	List<BookEntity> findBookByEditorial(EditorialEntity editorial);
}
