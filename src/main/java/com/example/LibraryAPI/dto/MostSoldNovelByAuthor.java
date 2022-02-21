package com.example.LibraryAPI.dto;

import lombok.*;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MostSoldNovelByAuthor implements Serializable{
        private String title;
        private int mostSoldNovel;
        private String author;
}
