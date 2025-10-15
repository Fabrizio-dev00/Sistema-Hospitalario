package com.hospital.hospital_system.controller;

import com.hospital.hospital_system.model.AntecedenteMedico;
import com.hospital.hospital_system.service.IAntecedenteMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/historias")
public class AntecedenteMedicoController {

    @Autowired
    private IAntecedenteMedicoService antecedenteMedicoService;

    @PostMapping("/{idHistoria}/antecedentes")
    public ResponseEntity<AntecedenteMedico> registrarAntecedente(
            @PathVariable Integer idHistoria,
            @RequestBody AntecedenteMedico antecedente) {

        try {
            AntecedenteMedico nuevoAntecedente = antecedenteMedicoService.registrarAntecedente(antecedente, idHistoria);
            return new ResponseEntity<>(nuevoAntecedente, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}