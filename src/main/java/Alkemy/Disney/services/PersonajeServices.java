package Alkemy.Disney.services;

import Alkemy.Disney.models.Personaje;

import java.util.List;

public interface PersonajeServices {

    List<Personaje> getAllPersonajes();

    List<Personaje> getByNombre(String nombre);

    List<Personaje> getByEdad(int edad);

    List<Personaje> getByFilme(String filme);

    List<Personaje> getByPeso(float peso);

}
