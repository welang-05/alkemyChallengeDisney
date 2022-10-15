package Alkemy.Disney.services.implementations;

import Alkemy.Disney.models.Filme;
import Alkemy.Disney.repositories.FilmeRepository;
import Alkemy.Disney.services.FilmeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeServicesImp implements FilmeServices {

    @Autowired
    FilmeRepository filmeRepository;

    @Override
    public List<Filme> getAllFilmes(){
        return filmeRepository.findAll();
    }

}
