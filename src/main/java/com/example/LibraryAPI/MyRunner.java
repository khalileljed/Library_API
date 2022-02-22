package com.example.LibraryAPI;

import com.example.LibraryAPI.model.*;
import com.example.LibraryAPI.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import static com.example.LibraryAPI.model.Category.*;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private KeywordRepository keywordRepository;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private MagazineRepository magazineRepository;
    @Autowired
    private NovelRepository novelRepository;


    @Override
    public void run(String... args) throws Exception {

        magazineRepository.deleteAll();
        keywordRepository.deleteAll();
        novelRepository.deleteAll();
        authorRepository.deleteAll();
        libraryRepository.deleteAll();

        Author auth1 = new Author("Bratislava", 43);
        Author auth2 = new Author("Lahbitri", 37);
        Author auth3 = new Author("Smitri", 32);
        Author auth4 = new Author("Nejma", 52);

        authorRepository.save(auth1);
        authorRepository.save(auth2);
        authorRepository.save(auth3);
        authorRepository.save(auth4);

        Library lib1 = new Library("Oxford", "Lac 1");
        Library lib2 = new Library("El9ods", "Lac 2");
        Library lib3 = new Library("Maxwell", "Lac 3");

        libraryRepository.save(lib1);
        libraryRepository.save(lib2);
        libraryRepository.save(lib3);
        Keyword keyword1 = new Keyword("Nada");
        Keyword keyword2 = new Keyword("News");

        keywordRepository.save(keyword1);
        keywordRepository.save(keyword2);

        Collection<Keyword> listKeywords = new ArrayList<>();
        listKeywords.add(keyword1);
        listKeywords.add(keyword2);
        magazineRepository.save(new Magazine("Sari7", 17, 256, Date.valueOf("2022-08-23"), 25, Historical, auth1, lib1,Date.valueOf("2022-03-31"),listKeywords));
        magazineRepository.save(new Magazine("Chourou9", 27, 512, Date.valueOf("2022-08-23"), 25, Crime, auth1, lib2,Date.valueOf("2023-03-31"),listKeywords));
        magazineRepository.save(new Magazine("PlayBoy", 37, 64, Date.valueOf("2022-08-23"), 22, Cooking, auth2, lib1,Date.valueOf("2022-03-31"),listKeywords));
        magazineRepository.save(new Magazine("Times", 12, 32, Date.valueOf("2022-08-23"), 25, Historical, auth2, lib2,Date.valueOf("2023-03-31"),listKeywords));
        magazineRepository.save(new Magazine("Jarida", 27, 128, Date.valueOf("2022-08-23"), 20, Cooking, auth3, lib1,Date.valueOf("2022-04-31"),listKeywords));
        magazineRepository.save(new Magazine("Jarida okhra", 37, 16, Date.valueOf("2022-08-23"), 25, Crime, auth3, lib2,Date.valueOf("2022-03-31"),listKeywords));
        magazineRepository.save(new Magazine("jarida maya9raha 7ad", 13, 0, Date.valueOf("2022-08-23"), 25, Historical, auth4, lib1,Date.valueOf("2023-03-31"),listKeywords));
        magazineRepository.save(new Magazine("The Matrix", 93, 8, Date.valueOf("2022-08-23"), 28, Historical, auth4, lib2,Date.valueOf("2023-03-31"),listKeywords));

        novelRepository.save(new Novel("Bou2ese2", 17, 256, new Date(2022,8,23), 25, Historical, auth1, lib1,"3bed mseken"));
        novelRepository.save(new Novel("Dankeshot", 27, 512, new Date(2022,8,23), 25, Crime, auth1, lib2,"we7ed teba3 la3bed lmseken"));
        novelRepository.save(new Novel("After", 37, 64, new Date(2022,8,23), 22, Cooking, auth2, lib1,"well a typical book"));
        novelRepository.save(new Novel("Bou5ale2", 12, 32, new Date(2022,8,23), 25, Historical, auth2, lib2,"3bed Bkhila"));
        novelRepository.save(new Novel("DeathNote", 27, 128, new Date(2022,8,23), 20, Cooking, auth3, lib1,"Kteb to9tel bih"));
        novelRepository.save(new Novel("Mou9admet IbnKhaldun", 37, 16, new Date(2022,8,23), 25, Crime, auth3, lib2,"bon haja m3a9da"));
        novelRepository.save(new Novel("Bible", 13, 0, new Date(2022,8,23), 25, Historical, auth4, lib1,"haja mch teb3etna"));
        novelRepository.save(new Novel("Age of Ace", 93, 8, new Date(2022,8,23), 28, Historical, auth4, lib2,"3amlou bih serie Game of Thrones"));

    }
}
