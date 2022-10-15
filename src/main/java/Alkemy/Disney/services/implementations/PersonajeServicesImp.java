package Alkemy.Disney.services.implementations;

import Alkemy.Disney.models.Personaje;
import Alkemy.Disney.repositories.PersonajeRepository;
import Alkemy.Disney.services.PersonajeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonajeServicesImp implements PersonajeServices {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Override
    public List<Personaje> getAllPersonajes(){
        return personajeRepository.findAll();
    }

    @Override
    public List<Personaje> getByNombre(String nombre){
        return personajeRepository.findByNombre(nombre);
    }

    @Override
    public List<Personaje> getByEdad(int edad){
        return personajeRepository.findByEdad(edad);
    }

    @Override
    public List<Personaje> getByFilme(String filme){
        return personajeRepository.findByFilmes_Titulo(filme);
    }

    @Override
    public List<Personaje> getByPeso(float peso){
        return personajeRepository.findByPeso(peso);
    }

}
