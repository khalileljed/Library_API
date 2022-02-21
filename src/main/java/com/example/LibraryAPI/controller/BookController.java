package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.dto.BookDto;
import com.example.LibraryAPI.exception.ResourceNotFoundException;
import com.example.LibraryAPI.model.Book;
import com.example.LibraryAPI.repository.BookRepository;
import com.example.LibraryAPI.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/")
public class BookController {
    private static final Logger LOGGER = LogManager.getLogger(BookController.class);

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookService bookService ;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/books")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable(value = "id") Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/books/byLibraryName")
    public List<BookDto> findByLibraryName(
            @RequestParam(name = "libraryName") String libraryName) {

            return bookService.findByLibraryName(libraryName,"prod");
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable(value = "id") Long bookId, @RequestBody Book bookDetails) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        book.setTitle(bookDetails.getTitle());
        book.setPrice(bookDetails.getPrice());
        book.setTotalUnitsSold(bookDetails.getTotalUnitsSold());
        book.setPublicationDate(bookDetails.getPublicationDate());
        book.setNbPages(bookDetails.getNbPages());
        book.setAuthor(bookDetails.getAuthor());
        book.setCategory(bookDetails.getCategory());
        book.setLibrary(bookDetails.getLibrary());


        return bookRepository.save(book);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        bookRepository.delete(book);

        return ResponseEntity.ok().build();
    }
}
