package com.example.LibraryAPI.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class AuthorDto implements Serializable {
    private Long id;
    private String name;
    private int age;

}
