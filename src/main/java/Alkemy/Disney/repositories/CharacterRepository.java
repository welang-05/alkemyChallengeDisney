package Alkemy.Disney.repositories;

import Alkemy.Disney.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CharacterRepository extends JpaRepository<Character, Long> {

    List<Character> findByName(String name);

    List<Character> findByAge(int age);

    List<Character> findByFilms_Title(String film);

    List<Character> findByWeight(float weight);

    List<Character> findByAgeAndFilms_Title(int age, String title);

    List<Character> findByAgeAndWeight(int age, float weight);

    List<Character> findByWeightAndFilms_Title(float weight, String title);

    List<Character> findByAgeAndWeightAndFilms_Title(int age, float weight, String title);

}
