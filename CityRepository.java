package com.example.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

// Faire de la recherche sur City
public interface CityRepository extends CrudRepository<City, Long> {
    List<City> findByName(String name);
}
