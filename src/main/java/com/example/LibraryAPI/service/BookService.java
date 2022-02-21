package com.example.LibraryAPI.service;

import com.example.LibraryAPI.dto.BookDto;
import com.example.LibraryAPI.dto.LibraryDto;
import com.example.LibraryAPI.model.Book;
import com.example.LibraryAPI.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository ;

    @Autowired
    private ModelMapper modelMapper ;

    public List<BookDto> getAllBooks(){
        return bookRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
    public List<BookDto> findByLibraryName(String libraryName,String profile) throws NullPointerException {
        if(profile == "prod") {
            List<BookDto> list = bookRepository.findByLibraryName(libraryName).stream().map(this::convertEntityToDto).collect(Collectors.toList());
            if (list.isEmpty()) {
                throw new NullPointerException();
            } else return list;
        }else if(profile == "test") {
            List<Book> list2 = bookRepository.findByLibraryName(libraryName);
            List<BookDto> list = new ArrayList<BookDto>();
            for(int i = 0; i < list2.size();i++)
            {
                list = list2.stream().map(x -> {
                            LibraryDto libDto = new LibraryDto(x.getLibrary().getId(),x.getLibrary().getName(),x.getLibrary().getAddress());
                            BookDto bookDto = new BookDto(x.getId(), x.getTitle(), x.getPrice(),x.getTotalUnitsSold(), x.getPublicationDate(),x.getNbPages(),x.getCategory(),null, libDto);
                    return bookDto;})
                    .collect(Collectors.toList());
            }
            if (list.isEmpty()) {
                throw new NullPointerException();
            } else return list;
        }else return null;
    }
    public BookDto convertEntityToDto(Book book){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        BookDto bookDto = new BookDto();
        bookDto = modelMapper.map(book , BookDto.class);
        return bookDto;
    }
    private Book convertDtoToEntity(BookDto bookDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Book book = new Book();
        book = modelMapper.map(bookDto , Book.class);
        return book;
    }
}
