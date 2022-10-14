package Alkemy.Disney.dtos;

import Alkemy.Disney.models.Genero;
import Alkemy.Disney.repositories.GeneroRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GeneroDTO {
    private long id;
    private String name, imagen;
    private List<FilmeDTO> filmes;

    public GeneroDTO(){}

    public GeneroDTO(Genero genero){
        this.id=genero.getId();
        this.imagen=genero.getImagen();
        this.name=genero.getName();
        this.filmes=genero.getFilmes().stream().map(FilmeDTO::new).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImagen() {
        return imagen;
    }

    public List<FilmeDTO> getFilmes() {
        return filmes;
    }
}
