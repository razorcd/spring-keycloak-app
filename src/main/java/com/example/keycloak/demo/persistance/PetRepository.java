package com.example.keycloak.demo.persistance;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PetRepository {

    private List<Pet> pets = new ArrayList<>();


    public void save(Pet pet) {
        pets.add(pet);
    }

    public List<Pet> findAll() {
        return pets;
    }
}
