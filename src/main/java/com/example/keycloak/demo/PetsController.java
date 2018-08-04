package com.example.keycloak.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    public RedirectView postPet(@Valid @ModelAttribute PetDto petDto) {
        pets.add(petDto);
        return new RedirectView("pets");
    }
}
