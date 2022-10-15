package Alkemy.Disney.repositories;

import Alkemy.Disney.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface FilmRepository extends JpaRepository<Film,Long> {

    List<Film> findByTitle(String title);

    List<Film> findByGenre_Name(String genre);

    List<Film> findByTitleAndGenre_Name(String title, String genre);


}
