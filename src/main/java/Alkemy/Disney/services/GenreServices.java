package Alkemy.Disney.services;

import Alkemy.Disney.models.Genre;

import java.util.List;

public interface GenreServices {

    List<Genre> getAllGenres();

    Genre getByName(String name);

    void saveGenre(Genre genre);

}
