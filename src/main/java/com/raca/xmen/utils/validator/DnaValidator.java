package com.raca.xmen.utils.validator;

public class DnaValidator {

    private static final String CHARACTER_REGEX = "[ATCG]+?";

    public static boolean isValid(String[] dna) {
        if (dna == null || dna.length == 0 || dna[0].length() < 2) {
            return false;
        }

        int minimunLength = dna.length;

        for (String string : dna) {
            if (string.length() != minimunLength) {
                return false;
            }
        }

        for (String chain : dna) {
            if (!chain.matches(CHARACTER_REGEX)) {
                return false;
            }
        }

        return true;
    }


}
