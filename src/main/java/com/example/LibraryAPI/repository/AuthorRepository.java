package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "authors", path = "authors")

public interface AuthorRepository extends JpaRepository<Author, Long> {
}