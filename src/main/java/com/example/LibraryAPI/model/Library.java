package com.example.LibraryAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "library")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "library", fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    public Library(String name, String address) {
        this.name = name;
        this.address = address;
    }
}