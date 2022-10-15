package Alkemy.Disney.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String image, title;
    private LocalDate creationDate;
    private int score;
    @ManyToMany(mappedBy = "films")
    private Set<Character> characters = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Film(){ }

    public Film(String image, String title, LocalDate creationDate, int score) {
        this.image = image;
        this.title = title;
        this.creationDate = creationDate;
        this.score = score;
    }

    public Film(String image, String title, LocalDate creationDate, int score, Genre genre) {
        this.image = image;
        this.title = title;
        this.creationDate = creationDate;
        this.score = score;
        this.genre = genre;
    }

    public Film(String image, String title, LocalDate creationDate, int score, Set<Character> characters) {
        this.image = image;
        this.title = title;
        this.creationDate = creationDate;
        this.score = score;
        this.characters = characters;
    }

    public Film(String image, String title, LocalDate creationDate, int score, Set<Character> characters, Genre genre) {
        this.image = image;
        this.title = title;
        this.creationDate = creationDate;
        this.score = score;
        this.characters = characters;
        this.genre = genre;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void addCharacter(Character character){
        this.characters.add(character);
        character.addFilm(this);
    }
}
