package com.example.LibraryAPI.service;

import com.example.LibraryAPI.dto.AuthorDto;
import com.example.LibraryAPI.model.Author;
import com.example.LibraryAPI.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository ;

    @Autowired
    private ModelMapper modelMapper ;

    public List<AuthorDto> getAllAuthors(){
        return authorRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private AuthorDto convertEntityToDto(Author author){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        AuthorDto authorDto = new AuthorDto();
        authorDto = modelMapper.map(author , AuthorDto.class);
        return authorDto;
    }
    private Author convertDtoToEntity(AuthorDto authorDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Author author = new Author();
        author = modelMapper.map(authorDto , Author.class);
        return author;
    }
}
