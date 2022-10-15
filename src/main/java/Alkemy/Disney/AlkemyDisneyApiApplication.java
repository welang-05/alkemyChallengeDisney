package Alkemy.Disney;

import Alkemy.Disney.models.Film;
import Alkemy.Disney.models.Genre;
import Alkemy.Disney.models.Character;
import Alkemy.Disney.models.User;
import Alkemy.Disney.repositories.FilmRepository;
import Alkemy.Disney.repositories.GenreRepository;
import Alkemy.Disney.repositories.CharacterRepository;
import Alkemy.Disney.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class AlkemyDisneyApiApplication {

	@Autowired
	public DelegatingPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AlkemyDisneyApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(FilmRepository filmRepository, GenreRepository genreRepository, CharacterRepository characterRepository, UserServices userServices){
		return (args) -> {
			Character character1 = new Character("https://static.tvtropes.org/pmwiki/pub/images/simba_4.png", "Simba","Un huérfano que se convirtió en rey", 5, 160);
			characterRepository.save(character1);

			Genre genre1 = new Genre("Musical", "as");
			genreRepository.save(genre1);

			Film film1 = new Film("as", "El Rey León", LocalDate.of(1994, 6, 24), 1, genre1);

			genre1.addFilm(film1);
			genreRepository.save(genre1);

			film1.addCharacter(character1);
			filmRepository.save(film1);
			characterRepository.save(character1);

			userServices.saveUser(new User("Walt@disney.com",passwordEncoder.encode("mouse")));

		};
	}

}
