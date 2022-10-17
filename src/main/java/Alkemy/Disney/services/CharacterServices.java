package Alkemy.Disney.services;

import Alkemy.Disney.models.Character;

import java.util.List;

public interface CharacterServices {

    List<Character> getAllCharacters();

    void saveCharacter(Character character);

    Character getById(Long id);

    List<Character> getByName(String name);

    List<Character> getByAge(int age);

    List<Character> getByFilm(String film);

    List<Character> getByWeight(float weight);

    List<Character> getByAgeAndFilm(int age, String film);

    List<Character> getByAgeAndWeight(int age, float weight);

    List<Character> getByWeightAndFilm(float weight, String title);

    List<Character> getByAgeAndWeightAndFilm(int age, float weight, String title);

    void deleteById(Long id);

}
