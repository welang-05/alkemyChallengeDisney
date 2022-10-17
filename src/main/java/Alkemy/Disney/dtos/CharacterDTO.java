package Alkemy.Disney.dtos;

import Alkemy.Disney.models.Film;
import Alkemy.Disney.models.Character;

import java.util.List;
import java.util.stream.Collectors;

public class CharacterDTO {
    private Long id;
    private String image, name, history;
    private int age;
    private float weight;
    private List<Object> films;

    public CharacterDTO(){}

    public CharacterDTO(Character character){
        this.id= character.getId();
        this.image = character.getImage();
        this.name = character.getName();
        this.history = character.getHistory();
        this.age = character.getAge();
        this.weight = character.getWeight();
        this.films = character.getFilms().stream().map(Film::getTitle).collect(Collectors.toList());
    }

    public CharacterDTO(Character character, boolean True){
        this.id= character.getId();
        this.image = character.getImage();
        this.name = character.getName();
        this.history = character.getHistory();
        this.age = character.getAge();
        this.weight = character.getWeight();
        this.films = character.getFilms().stream().map(FilmDTO::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getHistory() {
        return history;
    }

    public int getAge() {
        return age;
    }

    public float getWeight() {
        return weight;
    }

    public List<Object> getFilms() {
        return films;
    }
}
