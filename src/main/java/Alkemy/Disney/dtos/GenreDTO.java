package Alkemy.Disney.dtos;

import Alkemy.Disney.models.Genre;

import java.util.List;
import java.util.stream.Collectors;

public class GenreDTO {
    private long id;
    private String name, image;
    private List<FilmDTO> films;

    public GenreDTO(){}

    public GenreDTO(Genre genre){
        this.id= genre.getId();
        this.image = genre.getImage();
        this.name= genre.getName();
        this.films = genre.getFilms().stream().map(Filme -> new FilmDTO(Filme,true)).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public List<FilmDTO> getFilms() {
        return films;
    }
}
