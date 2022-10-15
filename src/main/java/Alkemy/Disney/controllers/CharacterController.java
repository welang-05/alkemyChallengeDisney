package Alkemy.Disney.controllers;

import Alkemy.Disney.services.CharacterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CharacterController {

    @Autowired
    private CharacterServices characterServices;

    @GetMapping("/characters")
    public List<Object> getCharacters(){
        return characterServices.getAllCharacters().stream().map(character -> {
            HashMap<String,Object> personajeSimple = new HashMap<String,Object>();
            personajeSimple.put("image", character.getImage());
            personajeSimple.put("name", character.getName());
            return personajeSimple;
        }).collect(Collectors.toList());
    }

}
