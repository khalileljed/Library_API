package com.example.LibraryAPI.service;

import com.example.LibraryAPI.dto.MagazineDto;
import com.example.LibraryAPI.model.Magazine;
import com.example.LibraryAPI.repository.MagazineRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MagazineService {

    @Autowired
    private MagazineRepository magazineRepository ;

    @Autowired
    private ModelMapper modelMapper ;

    public List<MagazineDto> getAllMagazines(){
        return magazineRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
    private MagazineDto convertEntityToDto(Magazine magazine){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        MagazineDto magazineDto = new MagazineDto();
        magazineDto = modelMapper.map(magazine, MagazineDto.class);
        return magazineDto;
    }
    private Magazine convertDtoToEntity(MagazineDto magazineDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Magazine magazine = new Magazine();
        magazine = modelMapper.map(magazineDto , Magazine.class);
        return magazine;
    }
}
