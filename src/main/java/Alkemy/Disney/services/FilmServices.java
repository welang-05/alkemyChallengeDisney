package Alkemy.Disney.services;

import Alkemy.Disney.models.Film;

import java.util.List;

public interface FilmServices {

    List<Film> getAllFilms();

    List<Film> getByTitle(String title);

    List<Film> getByGenre(String genre);

    List<Film> getByTitleAndGenre(String title, String genre);

    void deleteById(Long id);

    void saveFilm(Film film);

    Film getById(Long id);

}
