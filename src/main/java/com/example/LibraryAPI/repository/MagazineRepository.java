package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {
    @Query(value = "select *\n" +
            "from librarydb.magazine\n" +
            "where category = \"Cooking\" and next_release_date = (select min(next_release_date)\n" +
            "    from librarydb.magazine\n" +
            "    where next_release_date > date(now())\n" +
            ");", nativeQuery = true)
    Magazine getCookingMagazineClosestReleaseDate();
    public List<Magazine> findByLibraryName(String libraryName);

}