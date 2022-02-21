package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "novels", path = "novels")
public interface NovelRepository extends JpaRepository<Novel, Long> {
    @Query(value = "SELECT n.title bookTitle, max(n.total_units_sold) mostSoldNovel , a.name authorName FROM librarydb.novel n , librarydb.author a  where n.author_id = a.id group by a.name ;", nativeQuery = true)
    String[][] getMostSoldNovelByAuthor();

    public List<Novel> findByLibraryName(String libraryName);

}