package Alkemy.Disney.controllers;

import Alkemy.Disney.dtos.EditFilmDTO;
import Alkemy.Disney.dtos.FilmDTO;
import Alkemy.Disney.models.Character;
import Alkemy.Disney.models.Film;
import Alkemy.Disney.models.Genre;
import Alkemy.Disney.models.SortBy;
import Alkemy.Disney.services.CharacterServices;
import Alkemy.Disney.services.FilmServices;
import Alkemy.Disney.services.GenreServices;
import Alkemy.Disney.services.uploadsServices.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FilmController {

    @Autowired
    private FilmServices filmServices;

    @Autowired
    private CharacterServices characterServices;

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
    @PostMapping("/movies/add")
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

        if(score>5){
            return new ResponseEntity<>("The score it's a number between 1 and 5", HttpStatus.FORBIDDEN);
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
    @PatchMapping("/movies/edit")
    public ResponseEntity<Object> editFilm(
            @RequestBody EditFilmDTO editFilmDTO
    ){

        if(editFilmDTO==null){
            return new ResponseEntity<>("Missing some data", HttpStatus.FORBIDDEN);
        }

        Film film = filmServices.getById(editFilmDTO.getId());

        if(film==null){
            return new ResponseEntity<>("The film doesn't exist", HttpStatus.FORBIDDEN);
        }

        if(editFilmDTO.getScore()>5){
            return new ResponseEntity<>("The score it's a number between 1 and 5", HttpStatus.FORBIDDEN);
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
    @PatchMapping("/movies/editImage")
    public ResponseEntity<Object> editFilmImage(
            @RequestParam("image") MultipartFile image,
            @RequestParam Long id
    ){

        if(id==null){
            return new ResponseEntity<>("Film id not provided", HttpStatus.FORBIDDEN);
        }

        Film film = filmServices.getById(id);

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
    public ResponseEntity<Object> deleteFilm(
            @RequestParam Long id
    ){

        if(id==null){
            return new ResponseEntity<>("Film id not provided", HttpStatus.FORBIDDEN);
        }

        Film film = filmServices.getById(id);

        if(film==null){
            return new ResponseEntity<>("The film doesn't exist", HttpStatus.FORBIDDEN);
        }

        filmServices.deleteById(id);

        return new ResponseEntity<>("Film deleted correctly", HttpStatus.OK);
    }

    // ADD CHARACTER TO FILM
    @PostMapping("/movies/addCharacter")
    public ResponseEntity<Object> addCharacterToFilm(
            @RequestParam Long idFilm,
            @RequestParam Long idCharacter
    ){
        if(idCharacter==null){
            return new ResponseEntity<>("Character id not provided", HttpStatus.FORBIDDEN);
        }

        if(idFilm==null){
            return new ResponseEntity<>("Film id not provided", HttpStatus.FORBIDDEN);
        }

        Film film = filmServices.getById(idFilm);

        if(film==null){
            return new ResponseEntity<>("The film doesn't exist", HttpStatus.FORBIDDEN);
        }

        Character character = characterServices.getById(idCharacter);

        if(character==null){
            return new ResponseEntity<>("The character doesn't exist", HttpStatus.FORBIDDEN);
        }

        film.addCharacter(character);

        filmServices.saveFilm(film);
        characterServices.saveCharacter(character);

        return new ResponseEntity<>("Character added to Film correctly", HttpStatus.OK);
    }


}
