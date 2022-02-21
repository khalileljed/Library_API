package com.example.LibraryAPI.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class KeywordDto implements Serializable {
    private Long id;
    private String content;
}
