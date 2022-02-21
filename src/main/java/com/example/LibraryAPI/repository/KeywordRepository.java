package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "keywords", path = "keywords")

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}