package Alkemy.Disney.services.implementations;

import Alkemy.Disney.models.Character;
import Alkemy.Disney.repositories.CharacterRepository;
import Alkemy.Disney.services.CharacterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServicesImp implements CharacterServices {

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public Character getById(Long id){
        return characterRepository.findById(id).orElse(null);
    }

    @Override
    public List<Character> getAllCharacters(){
        return characterRepository.findAll();
    }

    @Override
    public void saveCharacter(Character character){
        characterRepository.save(character);
    }

    @Override
    public List<Character> getByName(String name){
        return characterRepository.findByName(name);
    }

    @Override
    public List<Character> getByAge(int age){
        return characterRepository.findByAge(age);
    }

    @Override
    public List<Character> getByFilm(String film){
        return characterRepository.findByFilms_Title(film);
    }

    @Override
    public List<Character> getByWeight(float weight){
        return characterRepository.findByWeight(weight);
    }

    @Override
    public List<Character> getByAgeAndFilm(int age, String film){
        return characterRepository.findByAgeAndFilms_Title(age, film);
    }

    @Override
    public List<Character> getByAgeAndWeight(int age, float weight){
        return characterRepository.findByAgeAndWeight(age, weight);
    }

    @Override
    public List<Character> getByWeightAndFilm(float weight, String title){
        return characterRepository.findByWeightAndFilms_Title(weight, title);
    }

    @Override
    public List<Character> getByAgeAndWeightAndFilm(int age, float weight, String title){
        return characterRepository.findByAgeAndWeightAndFilms_Title(age, weight, title);
    }

    @Override
    public void deleteById(Long id){
        characterRepository.deleteById(id);
    }

}
