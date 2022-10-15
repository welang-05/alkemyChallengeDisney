package Alkemy.Disney.dtos;

import Alkemy.Disney.models.Film;
import Alkemy.Disney.models.Character;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FilmDTO {
    private long id;
    private String image, title;
    private LocalDate creationDate;
    private int score;
    private List<Object> characterList;
    private String genre;

    public FilmDTO(){}

    public FilmDTO(Film film){
        this.id= film.getId();
        this.image = film.getImage();
        this.title = film.getTitle();
        this.creationDate = film.getCreationDate();
        this.score = film.getScore();
        this.characterList = film.getCharacters().stream().map(Character::getName).collect(Collectors.toList());
        this.genre = film.getGenre().getName();
    }

    public FilmDTO(Film film, boolean True){
        this.id= film.getId();
        this.image = film.getImage();
        this.title = film.getTitle();
        this.creationDate = film.getCreationDate();
        this.score = film.getScore();
        this.characterList = film.getCharacters().stream().map(CharacterDTO::new).collect(Collectors.toList());
        this.genre = film.getGenre().getName();
    }

    public long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public int getScore() {
        return score;
    }

    public List<Object> getCharacterList() {
        return characterList;
    }

    public String getGenre() {
        return genre;
    }
}
