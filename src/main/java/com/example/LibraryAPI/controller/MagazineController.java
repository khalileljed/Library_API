package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.dto.MagazineDto;
import com.example.LibraryAPI.exception.ResourceNotFoundException;
import com.example.LibraryAPI.model.Magazine;
import com.example.LibraryAPI.repository.MagazineRepository;
import com.example.LibraryAPI.service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/")
public class MagazineController {
    @Autowired
    MagazineRepository magazineRepository;
    @Autowired
    MagazineService magazineService ;

    @GetMapping("/magazines")
    public List<MagazineDto> getAllMagazines() {
        return magazineService.getAllMagazines();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/magazines/byLibraryName")
    public ResponseEntity<List<Magazine>> findByLibraryName(
            @RequestParam(name = "libraryName") String libraryName) {

        return new ResponseEntity<>(magazineRepository.findByLibraryName(libraryName), HttpStatus.OK);
    }

    @GetMapping("/magazines/getClosest")
    public Magazine getNovelMostSold() {
        return magazineRepository.getCookingMagazineClosestReleaseDate();
    }

    @PostMapping("/magazines")
    public Magazine createMagazine(@RequestBody Magazine magazine) {
        return magazineRepository.save(magazine);
    }

    @GetMapping("/magazines/{id}")
    public Magazine getMagazineById(@PathVariable(value = "id") Long magazineId) {
        return magazineRepository.findById(magazineId)
                .orElseThrow(() -> new ResourceNotFoundException("Magazine", "id", magazineId));
    }

    @PutMapping("/magazines/{id}")
    public Magazine updateMagazine(@PathVariable(value = "id") Long magazineId, @RequestBody Magazine magazineDetails) {

        Magazine magazine = magazineRepository.findById(magazineId)
                .orElseThrow(() -> new ResourceNotFoundException("Magazine", "id", magazineId));

        magazine.setTitle(magazineDetails.getTitle());
        magazine.setPrice(magazineDetails.getPrice());
        magazine.setTotalUnitsSold(magazineDetails.getTotalUnitsSold());
        magazine.setPublicationDate(magazineDetails.getPublicationDate());
        magazine.setNbPages(magazineDetails.getNbPages());
        magazine.setAuthor(magazineDetails.getAuthor());
        magazine.setCategory(magazineDetails.getCategory());
        magazine.setLibrary(magazineDetails.getLibrary());
        magazine.setNextReleaseDate(magazineDetails.getNextReleaseDate());
        magazine.setKeywords(magazineDetails.getKeywords());
        Magazine updatedMagazine = magazineRepository.save(magazine);
        return updatedMagazine;
    }

    @DeleteMapping("/magazines/{id}")
    public ResponseEntity<?> Magazine(@PathVariable(value = "id") Long magazineId) {
        Magazine magazine = magazineRepository.findById(magazineId)
                .orElseThrow(() -> new ResourceNotFoundException("Magazine", "id", magazineId));

        magazineRepository.delete(magazine);

        return ResponseEntity.ok().build();
    }
}
