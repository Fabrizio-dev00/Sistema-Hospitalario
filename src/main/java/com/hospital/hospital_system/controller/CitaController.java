package com.hospital.hospital_system.controller;

import com.hospital.hospital_system.model.Cita;
import com.hospital.hospital_system.service.ICitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private ICitaService citaService;

    @PostMapping
    public ResponseEntity<?> registrarCita(@Valid @RequestBody Cita cita) {
        try {
            Cita nuevaCita = citaService.registrarCita(cita);
            return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Cita>> obtenerTodasCitas(
            @RequestParam(required = false) Integer idMedico,
            @RequestParam(required = false) Integer idPaciente,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam(required = false) String estado) {

        LocalDateTime start = (fechaInicio != null) ? fechaInicio.atStartOfDay() : null;
        LocalDateTime end = (fechaFin != null) ? fechaFin.plusDays(1).atStartOfDay() : null;

        List<Cita> citas = citaService.buscarCitas(idMedico, idPaciente, start, end, estado);

        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

}