package com.hospital.hospital_system.controller;

import com.hospital.hospital_system.model.Medico;
import com.hospital.hospital_system.service.IMedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private IMedicoService medicoService;

    @PostMapping
    public ResponseEntity<Medico> registrarMedico(@Valid @RequestBody Medico medico) {
        Medico nuevoMedico = medicoService.guardarMedico(medico);
        return new ResponseEntity<>(nuevoMedico, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Medico>> obtenerMedicos(@RequestParam(required = false) String especialidad) {
        List<Medico> medicos;

        if (especialidad != null && !especialidad.isEmpty()) {
            medicos = medicoService.buscarPorEspecialidad(especialidad);
        } else {
            medicos = medicoService.obtenerTodosMedicos();
        }

        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerMedicoPorId(@PathVariable Integer id) {
        Optional<Medico> medico = medicoService.obtenerMedicoPorId(id);

        return medico.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizarMedico(@PathVariable Integer id, @Valid @RequestBody Medico detallesMedico) {
        Optional<Medico> medicoExistente = medicoService.obtenerMedicoPorId(id);

        if (medicoExistente.isPresent()) {
            Medico medico = medicoExistente.get();
            medico.setDni(detallesMedico.getDni());
            medico.setNombres(detallesMedico.getNombres());
            medico.setApellidos(detallesMedico.getApellidos());
            medico.setEspecialidad(detallesMedico.getEspecialidad());
            medico.setTelefono(detallesMedico.getTelefono());
            medico.setEstado(detallesMedico.getEstado());

            Medico medicoActualizado = medicoService.actualizarMedico(medico);
            return new ResponseEntity<>(medicoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarMedico(@PathVariable Integer id) {
        Optional<Medico> medico = medicoService.obtenerMedicoPorId(id);

        if (medico.isPresent()) {
            medicoService.desactivarMedico(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}