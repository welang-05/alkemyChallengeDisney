package Alkemy.Disney.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String name, image;
    @OneToMany(mappedBy = "genre", fetch=FetchType.EAGER)
    private Set<Film> films = new HashSet<>();

    public Genre() {  }

    public Genre(String name, String image) {
        this.name = name;
        this.image = image;
        this.films = new HashSet<>();
    }

    public Genre(String name, String image, Set<Film> films) {
        this.name = name;
        this.image = image;
        this.films = films;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    public void addFilm(Film film){
        this.films.add(film);
        film.setGenre(this);
    }
}
