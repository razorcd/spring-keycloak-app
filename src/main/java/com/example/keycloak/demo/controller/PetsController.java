package com.example.keycloak.demo.controller;

import com.example.keycloak.demo.persistance.Pet;
import com.example.keycloak.demo.persistance.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/pets")
public class PetsController {

    private PetRepository petRepository;

    @Autowired
    public PetsController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping
    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPet(@Valid @RequestBody Pet pet) {
        petRepository.save(pet);
    }
}
