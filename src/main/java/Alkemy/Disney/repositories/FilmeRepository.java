package Alkemy.Disney.repositories;

import Alkemy.Disney.models.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FilmeRepository extends JpaRepository<Filme,Long> {

}
