package com.example.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaApplication {
	// Verify the database content using the H2 web console: http://localhost:8080/h2-console
	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);


	}

	@Bean
	public CommandLineRunner demo(CityRepository repository) {
		return (args) -> {

			// Définir la date
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse("2018-10-09");
			System.out.println(date.toString());

			// Créer une Cité
			City paris = new City("Paris");
			Person tintin = new Person("Tintin", 20);
			paris.getPersons().add(tintin);
			tintin.setCity(paris);

			repository.save(paris);

			System.out.println("-------------------------------");
			System.out.println("Cities found with findAll():");
			for (City city : repository.findAll()) {
				System.out.println(city.toString());
			}

			System.out.println("-------------------------------");
			System.out.println("Persons associted with a city");
			Iterable<City> cities = repository.findAll();
			City c = cities.iterator().next();

			List<Person> persons = c.getPersons();
			System.out.println(persons.toString());

			System.out.println("--------------------------------------------");
			System.out.println("City found with findName('Paris'):");
			repository.findByName("Paris").forEach(city -> {
				System.out.println(city.toString());
			});

		};
	}

}
