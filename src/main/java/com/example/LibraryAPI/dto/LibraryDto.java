package com.example.LibraryAPI.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LibraryDto implements Serializable {
    private Long id;
    private String name;
    private String address;

}
