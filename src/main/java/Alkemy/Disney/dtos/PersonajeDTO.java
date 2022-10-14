package Alkemy.Disney.dtos;

import Alkemy.Disney.models.Personaje;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonajeDTO {
    private long id;
    private String imagen, nombre, historia;
    private int edad;
    private long peso;
    private List<FilmeDTO> filmes;

    public PersonajeDTO(){}

    public PersonajeDTO(Personaje personaje){
        this.id=personaje.getId();
        this.imagen= personaje.getImagen();
        this.nombre= personaje.getNombre();
        this.historia= personaje.getHistoria();
        this.edad= personaje.getEdad();
        this.peso= personaje.getPeso();
        this.filmes=personaje.getFilmes().stream().map(FilmeDTO::new).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public String getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHistoria() {
        return historia;
    }

    public int getEdad() {
        return edad;
    }

    public long getPeso() {
        return peso;
    }

    public List<FilmeDTO> getFilmes() {
        return filmes;
    }
}
