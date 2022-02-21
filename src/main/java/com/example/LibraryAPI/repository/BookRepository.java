package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "authors", path = "authors")

public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> findByLibraryName(String libraryName);

}