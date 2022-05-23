package com.raca.xmen.services;

import java.util.HashMap;

public interface IServiceDnaValidator {

    public boolean isMutant(String[] dna) throws Exception;

    public HashMap<String,Object> getStats() throws Exception;

}
