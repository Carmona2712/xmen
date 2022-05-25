package com.raca.xmen.models.Dtos;

import com.raca.xmen.models.Entities.Dna;
import com.raca.xmen.utils.messages.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
public class DnaDto implements Serializable {

    private String[] dna;
    private boolean isMutant;

    public DnaDto(String[] dna) {
        this.dna = dna;
        this.isMutant = false;
    }

    public DnaDto(String[] dna, boolean isMutant) {
        this.dna = dna;
        this.isMutant = isMutant;
    }

    public Dna convertToDnaEntity(){
        String dnaFixed = String.join(Constants.CONCATENATION_SYMBOL,this.dna);
        return new Dna(dnaFixed,this.isMutant);
    }

}
