package com.raca.xmen.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceDnaValidatorImplTest {


    @Autowired
    private ServiceDnaValidatorImpl serviceDnaValidator;

    @Test
    void isMutantIsValid() throws Exception {
        String dna[] = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        assertEquals(true,serviceDnaValidator.isMutant(dna));
    }

    @Test
    void isMutantIsInvalid() throws Exception {
        String dna[] = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
        assertEquals(false,serviceDnaValidator.isMutant(dna));
    }

    @Test
    void isMutantInvalidChain() throws Exception {
        String dna[] = {"ATGCGA","XXXX","TTATTT","AGACGG","GCGTCA","TCACTG"};
        assertThrows(Exception.class,() -> serviceDnaValidator.isMutant(dna));
    }

    @Test
    void getStats() throws Exception {
        assertNotNull(serviceDnaValidator.getStats());
    }


}