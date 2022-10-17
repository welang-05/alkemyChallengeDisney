package Alkemy.Disney.dtos;

import java.time.LocalDate;

public class EditFilmDTO {

    private Long id;
    private String image, title;
    private LocalDate creationDate;
    private int score;
    private String genre;

    public EditFilmDTO(){}

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public int getScore() {
        return score;
    }

    public String getGenre() {
        return genre;
    }
}
