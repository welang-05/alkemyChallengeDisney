package Alkemy.Disney;

import Alkemy.Disney.models.Filme;
import Alkemy.Disney.models.Genero;
import Alkemy.Disney.models.Personaje;
import Alkemy.Disney.repositories.FilmeRepository;
import Alkemy.Disney.repositories.GeneroRepository;
import Alkemy.Disney.repositories.PersonajeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class AlkemyDisneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlkemyDisneyApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(FilmeRepository filmeRepository, GeneroRepository generoRepository, PersonajeRepository personajeRepository){
		return (args) -> {
			Personaje personaje1 = new Personaje("https://static.tvtropes.org/pmwiki/pub/images/simba_4.png", "Simba","Un huérfano que se convirtió en rey", 5, 160);
			personajeRepository.save(personaje1);

			Genero genero1 = new Genero("Musical", "as");
			generoRepository.save(genero1);

			Filme filme1 = new Filme("as", "El Rey León", LocalDate.of(1994, 6, 24), 1, genero1);

			genero1.addFilme(filme1);
			generoRepository.save(genero1);

			filme1.addPersonaje(personaje1);
			filmeRepository.save(filme1);
			personajeRepository.save(personaje1);

		};
	}

}
