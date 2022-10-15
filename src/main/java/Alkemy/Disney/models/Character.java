package Alkemy.Disney.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String image, name, history;
    private int age;
    private float weight;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "Personaje_Filme", joinColumns = {@JoinColumn(name="personaje_id")}, inverseJoinColumns = {@JoinColumn(name="filme_id")})
    private Set<Film> films = new HashSet<>();

    public Character() { }

    public Character(String image, String name, String history, int age, long weight) {
        this.image = image;
        this.name = name;
        this.history = history;
        this.age = age;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilmes(Set<Film> films) {
        this.films = films;
    }

    public void addFilme(Film film){
        this.films.add(film);
    }
}
