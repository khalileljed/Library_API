package com.example.LibraryAPI.service;

import com.example.LibraryAPI.dto.MostSoldNovelByAuthor;
import com.example.LibraryAPI.dto.NovelDto;
import com.example.LibraryAPI.model.Novel;
import com.example.LibraryAPI.repository.NovelRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NovelService {

    @Autowired
    private NovelRepository NovelRepository ;

    @Autowired
    private ModelMapper modelMapper ;

    public List<NovelDto> getAllNovels(){
        return NovelRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
    public List<MostSoldNovelByAuthor> getMostSoldNovelByAuthor(){
        String[][] list2 = NovelRepository.getMostSoldNovelByAuthor();
        List<MostSoldNovelByAuthor> list = new ArrayList<MostSoldNovelByAuthor>();
            list = Arrays.stream(list2).map(x -> {
                        MostSoldNovelByAuthor mostSoldNovelByAuthor = new MostSoldNovelByAuthor(x[0],Integer.parseInt(x[1]),x[2]);
                        return mostSoldNovelByAuthor;})
                    .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new NullPointerException();
        } else return list;
    }
    private NovelDto convertEntityToDto(Novel novel){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        NovelDto novelDto = new NovelDto();
        novelDto = modelMapper.map(novel, NovelDto.class);
        return novelDto;
    }
    private Novel convertDtoToEntity(NovelDto novelDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Novel novel = new Novel();
        novel = modelMapper.map(novelDto , Novel.class);
        return novel;
    }
}
