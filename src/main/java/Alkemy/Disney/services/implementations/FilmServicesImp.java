package Alkemy.Disney.services.implementations;

import Alkemy.Disney.models.Film;
import Alkemy.Disney.repositories.FilmRepository;
import Alkemy.Disney.services.FilmServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServicesImp implements FilmServices {

    @Autowired
    FilmRepository filmRepository;

    @Override
    public List<Film> getAllFilms(){
        return filmRepository.findAll();
    }

    @Override
    public List<Film> getByTitle(String title){
        return filmRepository.findByTitle(title);
    }

    @Override
    public List<Film> getByGenre(String genre){
        return filmRepository.findByGenre_Name(genre);
    }

    @Override
    public List<Film> getByTitleAndGenre(String title, String genre){
        return filmRepository.findByTitleAndGenre_Name(title, genre);
    }

    @Override
    public void deleteById(Long id){
        filmRepository.deleteById(id);
    }

}
