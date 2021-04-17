package lt.savin.poker;

import lt.savin.poker.model.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class PokerApplication {
	public static final int HAND_SIZE = 5; // using int, because sublist method requires int, not long

	public static void main(String[] args) {
		SpringApplication.run(PokerApplication.class, args);
	}

	@Bean
	public void dataLoader() throws IOException {
		Path fileName = Path.of("poker.txt");
		String dataFileContent = Files.readString(fileName);

		List<Game> games = Arrays.stream(dataFileContent.split("\\n"))
				.map(Game::new)
				.collect(Collectors.toList());

		games.forEach(Game::declareGameOutcome);
	}


}




