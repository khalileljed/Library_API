package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.dto.AuthorDto;
import com.example.LibraryAPI.exception.ResourceNotFoundException;
import com.example.LibraryAPI.model.Author;
import com.example.LibraryAPI.repository.AuthorRepository;
import com.example.LibraryAPI.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @Autowired
    AuthorRepository authorRepository;

    @GetMapping("/authors")
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping("/authors")
    public Author createAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable(value = "id") Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("author", "id", authorId));
    }

    @PutMapping("/authors/{id}")
    public Author updateAuthor(@PathVariable(value = "id") Long authorId, @RequestBody Author authorDetails) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("author", "id", authorId));

        author.setName(authorDetails.getName());
        author.setAge(authorDetails.getAge());

        return authorRepository.save(author);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable(value = "id") Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("author", "id", authorId));

        authorRepository.delete(author);

        return ResponseEntity.ok().build();
    }
}

