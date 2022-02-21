package com.example.LibraryAPI.dto;

import com.example.LibraryAPI.model.Category;
import lombok.Data;

import java.io.Serializable;

@Data
public class NovelDto implements Serializable {
    private Long id;
    private String title;
    private float price;
    private int totalUnitsSold;
    private String publicationDate;
    private int nbPages;
    private Category category;
    private AuthorDto author;
    private LibraryDto library;
    private String storySummary;
}
