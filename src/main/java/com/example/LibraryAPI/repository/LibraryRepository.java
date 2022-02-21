package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "libraries", path = "libraries")

public interface LibraryRepository extends JpaRepository<Library, Long> {
}