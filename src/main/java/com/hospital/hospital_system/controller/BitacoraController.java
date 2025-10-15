package com.hospital.hospital_system.controller;

import com.hospital.hospital_system.model.Bitacora;
import com.hospital.hospital_system.service.IBitacoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bitacoras")
public class BitacoraController {

    @Autowired
    private IBitacoraService bitacoraService;

    @GetMapping
    public ResponseEntity<List<Bitacora>> obtenerBitacora() {

        List<Bitacora> bitacoras = bitacoraService.obtenerTodosLosRegistros();
        return new ResponseEntity<>(bitacoras, HttpStatus.OK);
    }
}