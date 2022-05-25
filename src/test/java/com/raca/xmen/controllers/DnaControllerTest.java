package com.raca.xmen.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raca.xmen.models.Dtos.DnaDto;
import com.raca.xmen.services.impl.ServiceDnaValidatorImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DnaControllerTest {

    @MockBean
    private ServiceDnaValidatorImpl serviceDna;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void isMutant() throws Exception {
        String dna[] = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
        DnaDto dnaDto = DnaDto.builder().dna(dna).build();

        mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(dnaDto))).andExpect(status().isForbidden());
    }

    @Test
    void isMutantBadRequest() throws Exception {
        DnaDto dnaDto = DnaDto.builder().build();
        mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dnaDto))).andExpect(status().isBadRequest());
    }

    @Test
    void getStats() throws Exception {
        mockMvc.perform(get("/stats")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}