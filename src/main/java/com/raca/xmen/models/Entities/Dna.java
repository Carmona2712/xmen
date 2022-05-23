package com.raca.xmen.models.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dnas")
public class Dna implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String dna;
    @Column
    private boolean isMutant;

    public Dna(String dna, boolean isMutant) {
        this.dna = dna;
        this.isMutant = isMutant;
    }


}
