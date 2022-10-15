package Alkemy.Disney.repositories;

import Alkemy.Disney.models.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {

    List<Personaje> findByNombre(String nombre);

    List<Personaje> findByEdad(int edad);

    List<Personaje> findByFilmes_Titulo(String filme);

    List<Personaje> findByPeso(float peso);

    List<Personaje> findByEdadAndFilmes_Titulo(int edad, String titulo);

    List<Personaje> findByEdadAndPeso(int edad, float peso);

    List<Personaje> findByPesoAndFilmes_Titulo(float peso, String titulo);

    List<Personaje> findByEdadAndPesoAndFilmes_Titulo(int edad, float peso, String titulo);

}
