package com.example.LibraryAPI.dto;

import com.example.LibraryAPI.model.Category;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
public class MagazineDto implements Serializable {
    private Long id;
    private String title;
    private float price;
    private int totalUnitsSold;
    private Date publicationDate;
    private int nbPages;
    private Category category;
    private AuthorDto author;
    private LibraryDto library;
    private Date nextReleaseDate;
    private List<KeywordDto> keywords;
}
