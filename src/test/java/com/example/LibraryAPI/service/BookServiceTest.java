package com.example.LibraryAPI.service;

import com.example.LibraryAPI.dto.BookDto;
import com.example.LibraryAPI.model.Book;
import com.example.LibraryAPI.model.Library;
import com.example.LibraryAPI.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.example.LibraryAPI.model.Category.Cooking;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {
    @InjectMocks
    private BookService booksService =new BookService();

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper modelMapper ;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void findByLibraryName_when_libraryName_is_null(){
        Assertions.assertThrows(NullPointerException.class ,()-> booksService.findByLibraryName(null,"test"),"error");
    }
   @Test
    public void findByLibraryName_when_libraryName_doesNot_exist(){
       Library lib = new Library(1L,"oxford","lac",null);
       List<Book> list = new ArrayList<Book>();
       Book book1 = new Book(1L, "book 1", 16.0f,10, null,45,Cooking,null, lib);
       Book book2 = new Book(2L, "book 2", 16.0f,10, null,45,Cooking,null, lib);
       Book book3 = new Book(3L, "book 3", 16.0f,10, null,45,Cooking,null, lib);

       list.add(book1);
       list.add(book2);
       list.add(book3);
       when(bookRepository.findByLibraryName("oxford")).thenReturn(list);



       List<BookDto> list2;
       list2 = booksService.findByLibraryName("oxford","test");
       assertNotEquals("xx", list2.get(0).getLibrary().getName());    }
    @Test
    public void findByLibraryName_when_libraryName_does_exist(){
        Library lib = new Library(1L,"oxford","lac",null);
        List<Book> list = new ArrayList<Book>();
        Book book1 = new Book(1L, "book 1", 16.0f,10, null,45,Cooking,null, lib);
        Book book2 = new Book(2L, "book 2", 16.0f,10, null,45,Cooking,null, lib);
        Book book3 = new Book(3L, "book 3", 16.0f,10, null,45,Cooking,null, lib);

        list.add(book1);
        list.add(book2);
        list.add(book3);
        when(bookRepository.findByLibraryName("oxford")).thenReturn(list);



        List<BookDto> list2;
        list2 = booksService.findByLibraryName("oxford","test");
        assertEquals("oxford", list2.get(0).getLibrary().getName());
    }
}