package Alkemy.Disney.services.implementations;

import Alkemy.Disney.models.Genre;
import Alkemy.Disney.repositories.GenreRepository;
import Alkemy.Disney.services.GenreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServicesImp implements GenreServices {

    @Autowired
    GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }

}
