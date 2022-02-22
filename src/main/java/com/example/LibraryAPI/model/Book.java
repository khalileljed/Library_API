package com.example.LibraryAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String title;
    private float price;
    private int totalUnitsSold;
    private Date publicationDate;
    private int nbPages;

    public Book(String title, float price, int totalUnitsSold, Date publicationDate, int nbPages, Category category, Author author, Library library) {
        this.title = title;
        this.price = price;
        this.totalUnitsSold = totalUnitsSold;
        this.publicationDate = publicationDate;
        this.nbPages = nbPages;
        this.category = category;
        this.author = author;
        this.library = library;
    }

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id")
    private Library library;
}