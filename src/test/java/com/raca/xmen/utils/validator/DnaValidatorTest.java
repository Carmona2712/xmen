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

    @Test
    void invalidDnaChainVoid() {
        String dna[] = {};
        assertEquals(false,DnaValidator.isValid(dna));
    }

    @Test
    void invalidDnaChainZero() {
        String dna[] = {""};
        assertEquals(false,DnaValidator.isValid(dna));
    }

    @Test
    void invalidDnaChainOne() {
        String dna[] = {"a"};
        assertEquals(false,DnaValidator.isValid(dna));
    }

    @Test
    void invalidDnaInvalidChain() {
        String dna[] = {"ATGCGA","CAGTGC","XXXX","AGACGG","GCGTCA","TCACTG"};
        assertEquals(false,DnaValidator.isValid(dna));
    }




}