package Alkemy.Disney.controllers;

import Alkemy.Disney.dtos.PersonajeDTO;
import Alkemy.Disney.services.PersonajeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonajeController {

    @Autowired
    private PersonajeServices personajeServices;

    @GetMapping("/api/characters")
    public List<Object> getPersonajes(){
        return personajeServices.getAllPersonajes().stream().map(personaje -> {
            HashMap<String,Object> personajeSimple = new HashMap<String,Object>();
            personajeSimple.put("imagen", personaje.getImagen());
            personajeSimple.put("nombre", personaje.getNombre());
            return personajeSimple;
        }).collect(Collectors.toList());
    }

}
