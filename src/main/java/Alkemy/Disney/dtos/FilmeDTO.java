package Alkemy.Disney.dtos;

import Alkemy.Disney.models.Filme;
import Alkemy.Disney.models.Genero;
import Alkemy.Disney.models.Personaje;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FilmeDTO {
    private long id;
    private String imagen, titulo;
    private LocalDate fechaCreacion;
    private int calificacion;
    private List<Object> listaPersonajes;
    private String genero;

    public FilmeDTO(){}

    public FilmeDTO(Filme filme){
        this.id= filme.getId();
        this.imagen=filme.getImagen();
        this.titulo=filme.getTitulo();
        this.fechaCreacion=filme.getFechaCreacion();
        this.calificacion= filme.getCalificacion();
        this.listaPersonajes =filme.getPersonajes().stream().map(Personaje::getNombre).collect(Collectors.toList());
        this.genero=filme.getGenero().getName();
    }

    public FilmeDTO(Filme filme, boolean True){
        this.id= filme.getId();
        this.imagen=filme.getImagen();
        this.titulo=filme.getTitulo();
        this.fechaCreacion=filme.getFechaCreacion();
        this.calificacion= filme.getCalificacion();
        this.listaPersonajes =filme.getPersonajes().stream().map(PersonajeDTO::new).collect(Collectors.toList());
        this.genero=filme.getGenero().getName();
    }

    public long getId() {
        return id;
    }

    public String getImagen() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public List<Object> getListaPersonajes() {
        return listaPersonajes;
    }

    public String getGenero() {
        return genero;
    }
}
