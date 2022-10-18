package Alkemy.Disney.controllers;

import Alkemy.Disney.models.Genre;
import Alkemy.Disney.services.FilmServices;
import Alkemy.Disney.services.GenreServices;
import Alkemy.Disney.services.uploadsServices.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class GenreController {

    @Autowired
    private GenreServices genreServices;

    @Autowired
    private FilmServices filmServices;

    @Autowired
    private StorageService storageService;

    // OBTAIN ALL GENRES

    @GetMapping("/genres")
    public List<Genre> getGenres(){
        return genreServices.getAllGenres();
    }

    @PostMapping("/genres/add")
    public ResponseEntity<Object> addGenre(
            @RequestParam("image") MultipartFile image,
            @RequestParam String name
    ){
        if( image==null || name==null){
            return new ResponseEntity<>("Missing some data", HttpStatus.FORBIDDEN);
        }

        Genre genre = genreServices.getByName(name);

        if(genre!=null) {
            return new ResponseEntity<>("Genre already exist", HttpStatus.FORBIDDEN);
        }

        //  All Ok
        storageService.store(image);
        Genre genre1 = new Genre(name, storageService.load(image.getOriginalFilename()).toString());

        genreServices.saveGenre(genre1);

        return new ResponseEntity<>("New genre added correctly", HttpStatus.CREATED);

    }

}
