package com.example.LibraryAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "magazine")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Magazine extends Book {
    private Date nextReleaseDate;
    @ManyToMany
    @JoinTable(
            name = "magazine_keywords",
            joinColumns = @JoinColumn(name = "magazine_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id", referencedColumnName = "id"))
    private Collection<Keyword> keywords ;

    public Magazine(String title, float price, int totalUnitsSold, Date publicationDate, int nbPages, Category category, Author author, Library library, Date nextReleaseDate, Collection<Keyword> keywords) {
        super(title, price, totalUnitsSold, publicationDate, nbPages, category, author, library);
        this.nextReleaseDate = nextReleaseDate;
        this.keywords = keywords;
    }
}