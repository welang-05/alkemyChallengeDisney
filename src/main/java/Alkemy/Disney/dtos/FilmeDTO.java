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
    private List<String> listaPersonajes;
    private Genero genero;

    public FilmeDTO(){}

    public FilmeDTO(Filme filme){
        this.id= filme.getId();
        this.imagen=filme.getImagen();
        this.titulo=filme.getTitulo();
        this.fechaCreacion=filme.getFechaCreacion();
        this.calificacion= filme.getCalificacion();
        this.listaPersonajes =filme.getPersonajes().stream().map(Personaje::getNombre).collect(Collectors.toList());;
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

    public List<String> getListaPersonajes() {
        return listaPersonajes;
    }

    public Genero getGenero() {
        return genero;
    }
}
