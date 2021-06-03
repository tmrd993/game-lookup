package com.timucin.gamelookup;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.timucin.gamelookup.domain.Game;
import com.timucin.gamelookup.domain.Shelf;
import com.timucin.gamelookup.domain.User;
import com.timucin.gamelookup.domain.Genre;
import com.timucin.gamelookup.service.ShelfService;
import com.timucin.gamelookup.service.UserService;
import com.timucin.gamelookup.service.GameService;
import com.timucin.gamelookup.service.GenreService;

@Component
public class DataLoader implements CommandLineRunner {
	
	private final GameService gameService;
	private final GenreService genreService;
	private final ShelfService gameCollectionService;
	private final PasswordEncoder passwordEncoder;
	private final UserService userService;
	
	@Autowired
	public DataLoader(GenreService genreService,
			ShelfService gameCollectionService,
			GameService gameService,
			PasswordEncoder passwordEncoder,
			UserService userService) {
		this.gameService = gameService;
		this.genreService = genreService;
		this.gameCollectionService = gameCollectionService;
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;	
	}

	@Override
	public void run(String... args) throws Exception {
		if(genreService.findAll().isEmpty()) {
			loadData();
		}
	}
	
	private void loadData() {
		User predefinedUser = new User();
		predefinedUser.setUsername("PredefinedUser");
		predefinedUser.setEmail("abc@example.de");
		predefinedUser.setPassword(passwordEncoder.encode("password"));
		
		Shelf predefinedCollection = new Shelf();
		predefinedCollection.setGames(new HashSet<>());
		predefinedCollection.setName("Predefined Collection");
		predefinedCollection.setShelfOwner(predefinedUser);
		
		Genre action = new Genre();
		action.setName("Action");
		
		Genre shooter = new Genre();
		shooter.setName("Shooter");
		
		Genre adventure = new Genre();
		adventure.setName("Adventure");
		
		Genre rpg = new Genre();
		rpg.setName("RPG");
		
		Game gameOne = new Game();
		gameOne.setName("Halo: Combat Evolved");
		gameOne.setCoverImageUrl("");
		gameOne.setEsrbRating("17+ Mature");
		gameOne.setGenres(new HashSet<>(Set.of(action, shooter, adventure)));
		gameOne.setMetacriticScore(90);
		gameOne.setPlatforms(Set.of("Xbox", "PC", "macOS"));
		gameOne.setReleaseDate(LocalDate.of(2001, 11, 15));
		
		Game gameTwo = new Game();
		gameTwo.setName("Clock Tower (1995)");
		gameTwo.setCoverImageUrl("");
		gameTwo.setEsrbRating("Not Rated");
		gameTwo.setGenres(new HashSet<>(Set.of(action, adventure)));
		gameTwo.setMetacriticScore(-1);
		gameTwo.setPlatforms(Set.of("PSP", "PlayStation", "PC", "Wii U", "Wii", "PlayStation 3", "PS Vita", "SNES"));
		gameTwo.setReleaseDate(LocalDate.of(1995, 9, 15));
		
		Game gameThree = new Game();
		gameThree.setName("Dark Souls");
		gameThree.setCoverImageUrl("");
		gameThree.setEsrbRating("17+ Mature");
		gameThree.setGenres(new HashSet<>(Set.of(action, adventure)));
		gameThree.setMetacriticScore(89);
		gameThree.setPlatforms(Set.of("Xbox 360", "PC", "PlayStation 3"));
		gameThree.setReleaseDate(LocalDate.of(2011, 9, 22));
		
		predefinedCollection.getGames().addAll(List.of(gameOne, gameTwo, gameThree));
		
		gameService.saveAll(List.of(gameOne, gameTwo, gameThree));
		userService.save(predefinedUser);
		gameCollectionService.save(predefinedCollection);
	}

}
