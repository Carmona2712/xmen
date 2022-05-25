package com.raca.xmen.controllers;

import com.raca.xmen.models.Dtos.DnaDto;
import com.raca.xmen.services.IServiceDnaValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DnaController {

    private static Logger log = LoggerFactory.getLogger(DnaController.class);

    @Autowired
    private IServiceDnaValidator dnaService;

    @PostMapping("/mutant")
    public ResponseEntity<?> isMutant(@RequestBody DnaDto dnaDto) {
        try {
            if(dnaDto.getDna() == null){
                throw new Exception();
            }else if (dnaService.isMutant(dnaDto.getDna())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception ex) {
            log.info("Error : "+ex.getStackTrace()[0].toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats() throws Exception {
            return ResponseEntity.status(HttpStatus.OK).body(dnaService.getStats());
    }




}
