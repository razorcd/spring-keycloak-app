package com.example.keycloak.demo;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Controller
@RequestMapping("pets")
public class PetsController {

    private List<PetDto> pets = new ArrayList<>();

    @GetMapping
    public ModelAndView getPets() {
        ModelAndView modelAndView = new ModelAndView("pets");
        modelAndView.addObject("pets", pets);
        return modelAndView;
    }

    @GetMapping("new")
    public ModelAndView newPet() {
        ModelAndView modelAndView = new ModelAndView("pets.new");
        modelAndView.addObject("newPet", new PetDto());
        return  modelAndView;
    }

    @PostMapping
    public RedirectView postPet(@ModelAttribute PetDto petDto) {
        pets.add(petDto);
        return new RedirectView("pets");
    }
}
