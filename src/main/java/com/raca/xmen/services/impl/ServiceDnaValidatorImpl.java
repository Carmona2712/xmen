package com.raca.xmen.services.impl;

import com.raca.xmen.models.Dtos.DnaDto;
import com.raca.xmen.models.Entities.Dna;
import com.raca.xmen.repositories.IDnaDao;
import com.raca.xmen.services.IServiceDnaValidator;
import com.raca.xmen.utils.messages.DnaMessages;
import com.raca.xmen.utils.validator.DnaValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;

@Service
public class ServiceDnaValidatorImpl implements IServiceDnaValidator {

    private static Logger log = LoggerFactory.getLogger(ServiceDnaValidatorImpl.class);

    @Autowired
    private IDnaDao dnaDao;


    @Override
    public boolean isMutant(String[] dna) throws Exception {

        if (!DnaValidator.isValid(dna)) {
            throw new Exception(DnaMessages.INVALID_REQUEST);
        }

        int countsSequencesValid = validatorFrontDirectionDnaWrapper(dna) + validatorDownDirectionDna(dna)
                + validatorDiagonalDownDirectionDna(dna) + validatorDiagonalUpDirectionDna(dna);

        log.info("Count Sequence: "+countsSequencesValid);

        if(countsSequencesValid>1){
            this.save(new DnaDto(dna,true).convertToDnaEntity());
            return true;
        }else{
            this.save(new DnaDto(dna,false).convertToDnaEntity());
            return false;
        }
    }


    private int validatorDiagonalUpDirectionDna(String[] dna) {
        int countSequence = 0;


        for (int i = dna.length - 3; i < dna.length; i++) {
            String[] letters = dna[i].split("");

            for (int j = 0; j < letters.length - 3; j++) {
                String letter = letters[j];

                int k = i - 1;
                int count = 1;
                while (count < 4) {
                    String diagonalDown = String.valueOf(dna[k].charAt(j + count));
                    if (letter.equals(diagonalDown)) {
                        count++;
                    } else {
                        break;
                    }
                    k--;
                }

                if (count == 4) {
                    countSequence++;
                }

            }
        }

        return countSequence;
    }


    private int validatorDiagonalDownDirectionDna(String[] dna) {
        int countSequence = 0;

        for (int i = 0; i < dna.length - 3; i++) {
            String[] letters = dna[i].split("");

            for (int j = 0; j < letters.length - 3; j++) {
                String letter = letters[j];

                int k = i + 1;
                int count = 1;
                while (count < 4) {
                    String diagonalDown = String.valueOf(dna[k].charAt(j + count));
                    if (letter.equals(diagonalDown)) {
                        count++;
                    } else {
                        break;
                    }
                    k++;
                }

                if (count == 4) {
                    countSequence++;
                }

            }
        }

        return countSequence;
    }


    private int validatorDownDirectionDna(String[] dna) {
        int countSequence = 0;

        for (int i = 0; i < dna.length - 3; i++) {
            String[] letters = dna[i].split("");

            for (int j = 0; j < letters.length; j++) {
                String letter = letters[j];

                int k = i + 1;
                int count = 1;
                while (count < 4) {
                    String down = String.valueOf(dna[k].charAt(j));
                    if (letter.equals(down)) {
                        count++;
                    } else {
                        break;
                    }
                    k++;
                }

                if (count == 4) {
                    countSequence++;
                }

            }
        }

        return countSequence;
    }


    private int validatorFrontDirectionDnaWrapper(String[] dna) {
        int countSequence = 0;

        for (int i = 0; i < dna.length; i++) {
            countSequence += validatorFrontDirectionDna(dna[i]);
        }

        return countSequence;
    }

    private int validatorFrontDirectionDna(String chain) {
        int countSequence = 0;

        String letter = "";
        for (int i = 0; i < chain.length() - 3; i++) {
            letter = String.valueOf(chain.charAt(i));

            int j = i + 1;
            int count = 1;
            while (count < 4) {
                if (letter.equals(String.valueOf(chain.charAt(j)))) {
                    count++;
                } else {
                    break;
                }
                j++;
            }

            if (count == 4) {
                countSequence++;
            }
        }

        return countSequence;
    }



    @Override
    public HashMap<String,Object> getStats() {
        HashMap map = new HashMap();
        DecimalFormat df = new DecimalFormat("#.##");
        long humansCount = dnaDao.countByIsMuntant(false);
        long mutantsCount = dnaDao.countByIsMuntant(true);
        double ratio = 0;
        if(humansCount>0){
            ratio = mutantsCount/humansCount;
        }else if(mutantsCount>0){
            ratio = 1;
        }

        map.put("count_mutant_dna",mutantsCount);
        map.put("count_human_dna",humansCount);
        map.put("ratio",ratio);
        return map;
    }

    public Dna save(Dna dna){
        return dnaDao.save(dna);
    };



}
