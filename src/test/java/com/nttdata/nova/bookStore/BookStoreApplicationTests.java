package com.nttdata.nova.bookStore;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import com.nttdata.nova.bookStore.repository.*;
import com.nttdata.nova.bookStore.entity.*;

@DataJpaTest

class BookStoreApplicationTests {

	@Autowired
    IBookRepository iBookRepository;

    @Autowired
    IEditorialRepository iEditorialRepository;
	
    @BeforeEach
    public void init(){
        BookEntity book1 = new BookEntity((long) 1,"TestTitle1","TestAuthor1", LocalDate.now(),11,"TestDescription1",null);
        BookEntity book2 = new BookEntity((long) 2,"TestTitle2","TestAuthor2", LocalDate.now(),12,"TestDescription2",null);

        EditorialEntity editorial1 = new EditorialEntity((long) 1,"Editorial1", Set.of(book1));
        EditorialEntity editorial2 = new EditorialEntity((long) 2,"Editorial2",Set.of(book2));

        iBookRepository.save(book1);
        iBookRepository.save(book2);
        iEditorialRepository.save(editorial1);
        iEditorialRepository.save(editorial2);
    }
  
	void contextLoads() {
    	
		@Nested
		@DisplayName("Book Tests")
		class BookTests{
			
			@Test
			@Order(1)
	        public void addBook(){
	
	            BookEntity newBook = new BookEntity((long) 3, "TestTitle3", "TestAuthor3", LocalDate.now(), 13, "TestDescription3", null);
	            int sizeBeforeInsert = iBookRepository.findAll().size();
	            iBookRepository.save(newBook);
	            int sizeAfterInsert = iBookRepository.findAll().size();
	
	            assertEquals(sizeBeforeInsert,sizeAfterInsert-1);
	        }
			
			@Test
			@Order(2)
	        public void getByTitle(){
	            List<BookEntity> request = iBookRepository.findAll();
	            String titleToFound = request.get(0).getTitle();

	            List<BookEntity> response = iBookRepository.findBookByTitle(titleToFound);
	            assertThat(response).isNotEmpty();
	        }
			
			@Test
			@Order(3)
			public void getByEditorial() {
				List<BookEntity> request = iBookRepository.findAll();
				EditorialEntity editorialToFound = request.get(0).getEditorialEntity();
				
				List<BookEntity> response = iBookRepository.findBookByEditorial(editorialToFound);
				assertThat(response).isNotEmpty();
			}
			
			@Test
			@Order(4)
	        public void updateBookById(){

	            BookEntity bookToUpdate = iBookRepository.findAll().get(0);
	            String titleBeforeUpdate = bookToUpdate.getTitle();
	            bookToUpdate.setTitle("NewTitleOfBook");

	            BookEntity bookUpdated = iBookRepository.save(bookToUpdate);
	            String titleAfterUpdate = bookUpdated.getTitle();
	            assertNotEquals(titleBeforeUpdate,titleAfterUpdate);

	        }
			
			@Test
			@Order(5)
	        public void deleteBookById(){

	            BookEntity bookToDelete = iBookRepository.findAll().get(0);
	            int sizeBeforeDeleted = iBookRepository.findAll().size();
	            iBookRepository.deleteById(bookToDelete.getId());
	            int sizeAfterDeleted = iBookRepository.findAll().size();
	            assertEquals(sizeBeforeDeleted,sizeAfterDeleted+1);
	        }
		}
		
		@Nested
		@DisplayName("Editorial Tests")
	    class EditorialTests{
			@Test
	        public void addNewEditorial() {
				EditorialEntity newEditorial = new EditorialEntity((long) 3, "Cerbero", Set.of());
	            int sizeBeforeSave = iEditorialRepository.findAll().size();
	            iEditorialRepository.save(newEditorial);
	            int sizeAfterSave = iEditorialRepository.findAll().size();
	            assertEquals(sizeAfterSave-1, sizeBeforeSave);
	        }
			
			@Test
	        public void getAllEditorials() {
	            List<EditorialEntity> allEditorials = iEditorialRepository.findAll();
	            assertThat(allEditorials).isNotEmpty();
	        }
			
			@Test
			public void getByName() {
				List<EditorialEntity> request = iEditorialRepository.findAll();
				String nameToFound = request.get(0).getName();
				List<EditorialEntity> response = iEditorialRepository.findByName(nameToFound);
				assertThat(response).isNotEmpty();
			}
			
			@Test
	        public void deleteEditorialById() {
	            long id = iEditorialRepository.findAll().get(0).getId();
	            int sizeBeforeSave = iEditorialRepository.findAll().size();
	            iEditorialRepository.deleteById(id);
	            int sizeAfterSave = iEditorialRepository.findAll().size();

	            assertEquals(sizeAfterSave+1, sizeBeforeSave);
	        }
			
			@Test
	        public void updateEditorial() {
	            EditorialEntity editorial = iEditorialRepository.findAll().get(0);
	            String oldName = editorial.getName();
	            String newName = "NewEditorialName";
	            editorial.setName(newName);
	            EditorialEntity updatedEditorial = iEditorialRepository.save(editorial);

	            assertNotEquals(updatedEditorial.getName(), oldName);
	            assertEquals(updatedEditorial.getName(), newName);
	        }
		}
	}
}
