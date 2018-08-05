package com.example.keycloak.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api")
public class MainController {

    private ObjectMapper objectMapper;

    @Autowired
    public MainController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("principal")
    public Principal getPrincipal(Principal principal) {
        return principal;
    }
}
