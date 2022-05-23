package com.raca.xmen.repositories;

import com.raca.xmen.models.Entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDnaDao extends JpaRepository<Dna,Integer> {

    @Query("Select count(d) from Dna d where d.isMutant = :isMutant")
    public Long countByIsMuntant(boolean isMutant);

}
