package com.raca.xmen.utils.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DnaValidatorTest {

    @Test
    void validDnaChain() {
        String dna[] = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
        assertEquals(true,DnaValidator.isValid(dna));
    }

    @Test
    void invalidDnaChain() {
        String dna[] = {"ATGCGA","CAGTGC","XXXX","AGACGG","GCGTCA","TCACTG"};
        assertEquals(false,DnaValidator.isValid(dna));
    }
}