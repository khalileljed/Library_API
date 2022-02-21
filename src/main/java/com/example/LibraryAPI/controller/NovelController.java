package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.dto.MostSoldNovelByAuthor;
import com.example.LibraryAPI.dto.NovelDto;
import com.example.LibraryAPI.exception.ResourceNotFoundException;
import com.example.LibraryAPI.model.Novel;
import com.example.LibraryAPI.repository.NovelRepository;
import com.example.LibraryAPI.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/")
public class NovelController {
    @Autowired
    NovelRepository novelRepository;
    @Autowired
    NovelService novelService ;

    @GetMapping("/novels")
    public List<NovelDto> getAllNovels() {
        return novelService.getAllNovels();
    }

    @GetMapping("/novels/getMostSold")
    public List<MostSoldNovelByAuthor> getMostSoldNovelByAuthor() {
        return novelService.getMostSoldNovelByAuthor();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/novels/byLibraryName")

    public ResponseEntity<List<Novel>> findByLibraryName(
            @RequestParam(name = "libraryName") String libraryName) {

        return new ResponseEntity<>(novelRepository.findByLibraryName(libraryName), HttpStatus.OK);
    }

    @PostMapping("/novels")
    public Novel createNovel(@RequestBody Novel novel) {
        return novelRepository.save(novel);
    }

    @GetMapping("/novels/{id}")
    public Novel getNovelById(@PathVariable(value = "id") Long novelId) {
        return novelRepository.findById(novelId)
                .orElseThrow(() -> new ResourceNotFoundException("Novel", "id", novelId));
    }

    @PutMapping("/novels/{id}")
    public Novel updateNovel(@PathVariable(value = "id") Long novelId, @RequestBody Novel novelDetails) {

        Novel novel = novelRepository.findById(novelId)
                .orElseThrow(() -> new ResourceNotFoundException("Novel", "id", novelId));

        novel.setTitle(novelDetails.getTitle());
        novel.setPrice(novelDetails.getPrice());
        novel.setTotalUnitsSold(novelDetails.getTotalUnitsSold());
        novel.setPublicationDate(novelDetails.getPublicationDate());
        novel.setNbPages(novelDetails.getNbPages());
        novel.setAuthor(novelDetails.getAuthor());
        novel.setCategory(novelDetails.getCategory());
        novel.setLibrary(novelDetails.getLibrary());
        novel.setStorySummary(novelDetails.getStorySummary());
        Novel updatedNovel = novelRepository.save(novel);
        return updatedNovel;
    }

    @DeleteMapping("/novels/{id}")
    public ResponseEntity<?> Novel(@PathVariable(value = "id") Long novelId) {
        Novel novel = novelRepository.findById(novelId)
                .orElseThrow(() -> new ResourceNotFoundException("Novel", "id", novelId));

        novelRepository.delete(novel);

        return ResponseEntity.ok().build();
    }
}
