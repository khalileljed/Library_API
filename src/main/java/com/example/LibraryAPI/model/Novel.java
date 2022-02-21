package com.example.LibraryAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "novel")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Novel extends Book {
    private String storySummary;
}