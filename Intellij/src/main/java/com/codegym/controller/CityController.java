package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.service.city.ICityService;
import com.codegym.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class CityController {
    @Autowired
    ICityService cityService;
    @Autowired
    ICountryService countryService;

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> findAllCountry() {
        return new ResponseEntity(countryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<City>> findAllCity() {
        return new ResponseEntity(cityService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/cities")
    public ResponseEntity<Void> creatCity(@RequestBody City city) {
        cityService.save(city);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/cities/{id}")
    public ResponseEntity<Void> updateCity(@RequestBody City city, @PathVariable("id") Long id) {
        Optional<City> city1 = cityService.findById(id);
        if (!city1.isPresent()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        city.setId(id);
        cityService.save(city);
        return new ResponseEntity(HttpStatus.OK);
    }
@DeleteMapping("/cities/{id}")
public ResponseEntity<City> deleteCity(@PathVariable("id") Long id) {
   cityService.remove(id);
    return new ResponseEntity(HttpStatus.OK);
}
    @GetMapping("/cities/{id}")
    public ResponseEntity<City> findOne(@PathVariable("id") Long id) {
        Optional<City> city = cityService.findById(id);
        return new ResponseEntity(city.get(), HttpStatus.OK);
    }
}
