package com.nttdata.nova.bookStore.collections;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.nova.bookStore.collections.BookRegistryEntity;

@Repository

public interface IBookRegistryRepository extends MongoRepository<BookRegistryEntity, Long>{
}
