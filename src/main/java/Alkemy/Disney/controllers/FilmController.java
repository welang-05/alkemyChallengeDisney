package Alkemy.Disney.controllers;

import Alkemy.Disney.dtos.EditCharacterDTO;
import Alkemy.Disney.dtos.EditFilmDTO;
import Alkemy.Disney.dtos.FilmDTO;
import Alkemy.Disney.models.Character;
import Alkemy.Disney.models.Film;
import Alkemy.Disney.models.Genre;
import Alkemy.Disney.models.SortBy;
import Alkemy.Disney.services.FilmServices;
import Alkemy.Disney.services.GenreServices;
import Alkemy.Disney.services.uploadsServices.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FilmController {

    @Autowired
    private FilmServices filmServices;

    @Autowired
    private GenreServices genreServices;

    @Autowired
    private StorageService storageService;

    //  GET ALL MOVIES
    @GetMapping("/movies")
    public List<Object> getCharacters()
    {
        return filmServices.getAllFilms().stream().map(film -> {
            HashMap<String,Object> filmSimple = new HashMap<String,Object>();
            filmSimple.put("image", film.getImage());
            filmSimple.put("title", film.getTitle());
            filmSimple.put("creationDate", film.getCreationDate());
            return filmSimple;
        }).collect(Collectors.toList());
    }

    // FILTER
    @GetMapping(value = "/movies", params = {"name", "genre", "order"})
    public List<FilmDTO> filterCharacters(
            @RequestParam String name,
            @RequestParam String genre,
            @RequestParam SortBy sortBy
            ){

        List <FilmDTO> films = filmServices.getByTitleAndGenre(name,genre).stream().map(film -> new FilmDTO(film, true)).collect(Collectors.toList());

        if(sortBy==SortBy.ASC) {
            films.sort(Comparator.comparing(FilmDTO::getCreationDate));
        }
        else if(sortBy==SortBy.DESC){
            films.sort(Comparator.comparing(FilmDTO::getCreationDate).reversed());
        }

        return films;

    }

    //  CREATE FILM
    @PostMapping("/film/add")
    public ResponseEntity<Object> addFilm(
            @RequestParam("image") MultipartFile image,
            @RequestParam String title,
            @RequestParam LocalDate creationDate,
            @RequestParam int score,
            @RequestParam String genre
    ) {

        if( image==null || title==null || creationDate==null || score<=0 || genre==null ){
            return new ResponseEntity<>("Missing some data", HttpStatus.FORBIDDEN);
        }

        if( !filmServices.getByTitle(title).isEmpty() ){
            return new ResponseEntity<>("The film already exists", HttpStatus.FORBIDDEN);
        }
//      All Ok
        storageService.store(image);

        Genre genre1 = genreServices.getByName(genre);

        Film film = new Film(storageService.load(image.getOriginalFilename()).toString(), title,creationDate,score,genre1);

        filmServices.saveFilm(film);

        return new ResponseEntity<>("New film added correctly", HttpStatus.CREATED);
    }


    //  EDIT DATA
    @PostMapping("/movies/edit")
    public ResponseEntity<Object> editFilm(
            @RequestBody EditFilmDTO editFilmDTO
    ){

        if(editFilmDTO==null){
            return new ResponseEntity<>("Missing some data", HttpStatus.FORBIDDEN);
        }

        Film film = filmServices.geById(editFilmDTO.getId());

        if(film==null){
            return new ResponseEntity<>("The film doesn't exist", HttpStatus.FORBIDDEN);
        }

        if(editFilmDTO.getScore()>0){
            film.setScore(editFilmDTO.getScore());
        }

        if(editFilmDTO.getTitle()!=null){
            film.setTitle(editFilmDTO.getTitle());
        }

        if(editFilmDTO.getCreationDate()!=null){
            film.setCreationDate(editFilmDTO.getCreationDate());
        }

        if(editFilmDTO.getGenre()!=null){
            Genre genre1 = genreServices.getByName(editFilmDTO.getGenre());
            film.setGenre(genre1);
        }

        filmServices.saveFilm(film);

        return new ResponseEntity<>("Film edited correctly", HttpStatus.OK);
    }


    //  EDIT IMAGE
    @PostMapping("/movies/editImage")
    public ResponseEntity<Object> editFilmImage(
            @RequestParam("image") MultipartFile image,
            @RequestParam Long id
    ){

        if(id==null){
            return new ResponseEntity<>("Film id not provided", HttpStatus.FORBIDDEN);
        }

        Film film = filmServices.geById(id);

        if(film==null){
            return new ResponseEntity<>("The film doesn't exist", HttpStatus.FORBIDDEN);
        }

        storageService.store(image);

        film.setImage(image.getOriginalFilename());

        filmServices.saveFilm(film);

        return new ResponseEntity<>("New film image uploaded correctly", HttpStatus.OK);

    }

    //  DELETE FILM
    @DeleteMapping("/movies/delete")
    public ResponseEntity<Object> deleteCharacter(
            @RequestParam Long id
    ){

        if(id==null){
            return new ResponseEntity<>("Film id not provided", HttpStatus.FORBIDDEN);
        }

        Film film = filmServices.geById(id);

        if(film==null){
            return new ResponseEntity<>("The film doesn't exist", HttpStatus.FORBIDDEN);
        }

        filmServices.deleteById(id);

        return new ResponseEntity<>("Film deleted correctly", HttpStatus.OK);
    }




}
