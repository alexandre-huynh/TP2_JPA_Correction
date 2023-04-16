package com.example.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestWebService {
    CityRepository cityRepository;

    @Autowired
    public RestWebService(CityRepository cityRepository){
        super();
        this.cityRepository = cityRepository;
    }

    @GetMapping("/cities")
    public Iterable<City> getCities(){
        // http://localhost:8080/cities
        // curl -H "Content-Type: application/json" -X GET http://localhost:8080/cities
        return cityRepository.findAll();
    }

    @GetMapping("/cities/{name}")
    public List<City> getCitiesByName(@PathVariable("name") String name){
        // http://localhost:8080/cities/Paris
        // curl -H "Content-Type: application/json" -X GET http://localhost:8080/cities/Paris
        return cityRepository.findByName(name);
    }

    @PostMapping("/cities")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Iterable<City> addCity(@RequestBody City city) throws Exception {
        // curl -v -H "Content-Type: application/json" -X POST -d '{"person":{"name":"toto", "age":38}, "name":"Boston"}' http://localhost:8080/cities
        cityRepository.save(city);
        if(city.getName().equals("London")){
            throw new Exception();
        }
        return cityRepository.findAll();
    }
}
