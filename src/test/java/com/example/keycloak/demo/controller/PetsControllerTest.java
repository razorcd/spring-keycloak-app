package com.example.keycloak.demo.controller;

import com.example.keycloak.demo.persistance.Pet;
import com.example.keycloak.demo.persistance.PetRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class PetsControllerTest {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void postPets_authorised_test() throws Exception {
        Pet newPet = new Pet("pet 1");
        mockMvc.perform(post("/api/pets").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPet)))
                .andExpect(status().isCreated());
    }

    @Test
    public void postPets_unauthorised_test() throws Exception {
        Pet newPet = new Pet("pet 1");
        mockMvc.perform(post("/api/pets").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPet)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getPets_test() throws Exception {
        //given
        Pet newPet = new Pet("pet 1");
        petRepository.save(newPet);

        //when
        MvcResult response = mockMvc.perform(get("/api/pets").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //then
        List<Pet> petList = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<List<Pet>>(){});
        assertEquals("Should return created pet.", petList.get(0), newPet);
    }
}
