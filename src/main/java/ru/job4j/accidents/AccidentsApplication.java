package ru.job4j.accidents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

@SpringBootApplication
public class AccidentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccidentsApplication.class, args);
	}

}
