package Alkemy.Disney.controllers;

import Alkemy.Disney.dtos.CharacterDTO;
import Alkemy.Disney.dtos.EditCharacterDTO;
import Alkemy.Disney.models.Character;
import Alkemy.Disney.models.Film;
import Alkemy.Disney.services.CharacterServices;
import Alkemy.Disney.services.FilmServices;
import Alkemy.Disney.services.uploadsServices.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CharacterController {

    @Autowired
    private CharacterServices characterServices;

    @Autowired
    private FilmServices filmServices;

    @Autowired
    private StorageService storageService;

    //  GET ALL CHARACTERS
    @GetMapping("/characters")
    public List<Object> getCharacters()
    {
        return characterServices.getAllCharacters().stream().map(character -> {
            HashMap<String,Object> characterSimple = new HashMap<String,Object>();
            characterSimple.put("image", character.getImage());
            characterSimple.put("name", character.getName());
            return characterSimple;
        }).collect(Collectors.toList());
    }


    //  GET PARTICULAR CHARACTER
    @GetMapping("/characters/details")
    public Object getCharacter(
            @RequestParam Long id
    ){
        if(id==null){
            return new ResponseEntity<>("Character id not provided", HttpStatus.FORBIDDEN);
        }

        Character character = characterServices.getById(id);

        if(character==null){
            return new ResponseEntity<>("The character doesn't exist", HttpStatus.FORBIDDEN);
        }

        return new CharacterDTO(character, true);

    }


    //  FILTER BY NAME
    @GetMapping(value = "/characters", params = "name")
    public List<Object> getCharactersByName(
            @RequestParam String name
    ){
        return characterServices.getByName(name).stream().map(CharacterDTO::new).collect(Collectors.toList());
    }


    //  FILTER BY AGE
    @GetMapping(value = "/characters", params = "age")
    public List<Object> getCharactersByAge(
            @RequestParam int age
    ){
        return characterServices.getByAge(age).stream().map(CharacterDTO::new).collect(Collectors.toList());
    }


    //  FILTER BY FILM
    @GetMapping(value = "/characters", params = "movies")
    public List<Object> getCharactersByFilm(
            @RequestParam String movies
    ){
        return characterServices.getByFilm(movies).stream().map(CharacterDTO::new).collect(Collectors.toList());
    }


    //  FILTER BY WEIGHT, AGE AND FILM
    @GetMapping(value = "/characters", params = {"weight","age","movie"})
    public List<Object> getCharactersByNameAndAge(
            @RequestParam float weight,
            @RequestParam String movie,
            @RequestParam int age
    ){
        if(weight<0){
            return characterServices.getByAgeAndFilm(age, movie).stream().map(CharacterDTO::new).collect(Collectors.toList());
        }

        if(age<0){
            return characterServices.getByWeightAndFilm(weight,movie).stream().map(CharacterDTO::new).collect(Collectors.toList());
        }

        if(movie==null){
            return characterServices.getByAgeAndWeight(age, weight).stream().map(CharacterDTO::new).collect(Collectors.toList());
        }

    return characterServices.getByAgeAndWeightAndFilm(age, weight, movie).stream().map(CharacterDTO::new).collect(Collectors.toList());

    }


    //  CREATE CHARACTER
    @PostMapping("/characters/add")
    public ResponseEntity<Object> addCharacter(
            @RequestParam("image") MultipartFile image,
            @RequestParam String name,
            @RequestParam String history,
            @RequestParam int age,
            @RequestParam float weight
            ) {

        if( image==null || name==null || history==null || age<=0 || weight<=0 ){
            return new ResponseEntity<>("Missing some data", HttpStatus.FORBIDDEN);
        }

        if( !characterServices.getByName(name).isEmpty() ){
            return new ResponseEntity<>("The character already exists", HttpStatus.FORBIDDEN);
        }
    //      All Ok
        storageService.store(image);

        Character character = new Character(storageService.load(image.getOriginalFilename()).toString(), name, history, age, weight);

        characterServices.saveCharacter(character);


        return new ResponseEntity<>("New character created correctly", HttpStatus.CREATED);
    }


    //  EDIT DATA
    @PatchMapping("/characters/edit")
    public ResponseEntity<Object> editCharacter(
            @RequestBody EditCharacterDTO editCharacterDTO
            ){

        if(editCharacterDTO==null){
            return new ResponseEntity<>("Missing some data", HttpStatus.FORBIDDEN);
        }

        Character character = characterServices.getById(editCharacterDTO.getId());

        if(character==null){
            return new ResponseEntity<>("The character doesn't exist", HttpStatus.FORBIDDEN);
        }

        if(editCharacterDTO.getAge()>0){
            character.setAge(editCharacterDTO.getAge());
        }

        if(editCharacterDTO.getWeight()>0){
            character.setWeight(editCharacterDTO.getWeight());
        }

        if(editCharacterDTO.getName()!=null){
            character.setName(editCharacterDTO.getName());
        }

        if(editCharacterDTO.getHistory()!=null){
            character.setHistory(editCharacterDTO.getHistory());
        }

        characterServices.saveCharacter(character);

        return new ResponseEntity<>("Character edited correctly", HttpStatus.OK);
    }


    //  EDIT IMAGE
    @PatchMapping("/characters/editImage")
    public ResponseEntity<Object> editCharacterImage(
            @RequestParam("image") MultipartFile image,
            @RequestParam Long id
    ){

        if(id==null){
            return new ResponseEntity<>("Character id not provided", HttpStatus.FORBIDDEN);
        }

        Character character = characterServices.getById(id);

        if(character==null){
            return new ResponseEntity<>("The character doesn't exist", HttpStatus.FORBIDDEN);
        }

        storageService.store(image);

        character.setImage(image.getOriginalFilename());

        characterServices.saveCharacter(character);

        return new ResponseEntity<>("New character image uploaded correctly", HttpStatus.OK);

    }

    //  DELETE CHARACTER
    @DeleteMapping("/characters/delete")
    public ResponseEntity<Object> deleteCharacter(
            @RequestParam Long id
    ){

        if(id==null){
            return new ResponseEntity<>("Character id not provided", HttpStatus.FORBIDDEN);
        }

        Character character = characterServices.getById(id);

        if(character==null){
            return new ResponseEntity<>("The character doesn't exist", HttpStatus.FORBIDDEN);
        }

        characterServices.deleteById(id);

        return new ResponseEntity<>("Character deleted correctly", HttpStatus.OK);
    }

    // ADD FILM TO CHARACTER
    @PostMapping("/characters/addFilm")
    public ResponseEntity<Object> addFilmToCharacter(
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

        return new ResponseEntity<>("Film added to character correctly", HttpStatus.OK);
    }

}
