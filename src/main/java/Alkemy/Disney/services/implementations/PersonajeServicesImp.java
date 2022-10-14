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

}
