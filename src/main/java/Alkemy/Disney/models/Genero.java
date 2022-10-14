package Alkemy.Disney.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String name, imagen;
    @OneToMany(mappedBy = "genero", fetch=FetchType.EAGER)
    private Set<Filme> filmes = new HashSet<>();

    public Genero() {  }

    public Genero(String name, String imagen) {
        this.name = name;
        this.imagen = imagen;
        this.filmes = new HashSet<>();
    }

    public Genero(String name, String imagen, Set<Filme> filmes) {
        this.name = name;
        this.imagen = imagen;
        this.filmes = filmes;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Set<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(Set<Filme> filmes) {
        this.filmes = filmes;
    }

    public void addFilme(Filme filme){
        this.filmes.add(filme);
        filme.setGenero(this);
    }
}
