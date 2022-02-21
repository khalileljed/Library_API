package com.example.LibraryAPI.dto;

import com.example.LibraryAPI.model.Category;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Data

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDto implements Serializable {
    private Long id;
    private String title;
    private float price;
    private int totalUnitsSold;
    private Date publicationDate;
    private int nbPages;
    private Category category;
    private AuthorDto author;
    private LibraryDto library;

}