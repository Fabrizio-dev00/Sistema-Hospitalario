package com.hospital.hospital_system.controller;

import com.hospital.hospital_system.model.Paciente;
import com.hospital.hospital_system.service.IPacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat; // Importar para manejar fechas en RequestParam

import java.time.LocalDate; // Importar para el manejo de fechas de nacimiento
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;

    // -----------------------------------------------------
    // RF1: CRUD - REGISTRO
    // -----------------------------------------------------

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@Valid @RequestBody Paciente paciente) {
        Paciente nuevoPaciente = pacienteService.guardarPaciente(paciente);
        return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED); // 201 Created
    }

    // -----------------------------------------------------
    // RF1: CRUD - CONSULTAR TODOS
    // -----------------------------------------------------

    @GetMapping
    public ResponseEntity<List<Paciente>> obtenerPacientes() {
        List<Paciente> pacientes = pacienteService.obtenerTodosPacientes();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    // -----------------------------------------------------
    // RF1: CRUD - CONSULTAR POR ID
    // -----------------------------------------------------

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPacientePorId(@PathVariable Integer id) {
        Optional<Paciente> paciente = pacienteService.obtenerPacientePorId(id);

        if (paciente.isPresent()) {
            return new ResponseEntity<>(paciente.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // -----------------------------------------------------
    // RF1: CRUD - ACTUALIZAR
    // -----------------------------------------------------

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizarPaciente(@PathVariable Integer id, @Valid @RequestBody Paciente detallesPaciente) {
        Optional<Paciente> pacienteExistente = pacienteService.obtenerPacientePorId(id);

        if (pacienteExistente.isPresent()) {
            Paciente paciente = pacienteExistente.get();
            // Asignar los nuevos detalles. La validación de ID se hace en el service.
            paciente.setDni(detallesPaciente.getDni());
            paciente.setNombres(detallesPaciente.getNombres());
            paciente.setApellidos(detallesPaciente.getApellidos());
            paciente.setFechaNacimiento(detallesPaciente.getFechaNacimiento());
            paciente.setSexo(detallesPaciente.getSexo());
            paciente.setDireccion(detallesPaciente.getDireccion());
            paciente.setTelefono(detallesPaciente.getTelefono());
            paciente.setCorreo(detallesPaciente.getCorreo());
            paciente.setEstado(detallesPaciente.getEstado()); // Permite actualizar el estado (si es necesario)

            Paciente pacienteActualizado = pacienteService.actualizarPaciente(paciente);
            return new ResponseEntity<>(pacienteActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // -----------------------------------------------------
    // RF1: CRUD - DESACTIVAR (Soft Delete)
    // -----------------------------------------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarPaciente(@PathVariable Integer id) {
        Optional<Paciente> paciente = pacienteService.obtenerPacientePorId(id);

        if (paciente.isPresent()) {
            pacienteService.desactivarPaciente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // -----------------------------------------------------
    // RF11, RF12: FILTROS y REPORTES
    // -----------------------------------------------------

    /**
     * RF11, RF12: Filtros de consulta avanzados.
     * Permite filtrar por estado (RF12) o por rango de fechas de nacimiento (RF11).
     * Método: GET /api/pacientes/reporte?estado=...
     * Método: GET /api/pacientes/reporte?fechaNacimientoInicio=...&fechaNacimientoFin=...
     */
    @GetMapping("/reporte")
    public ResponseEntity<List<Paciente>> obtenerReportePacientes(
            @RequestParam(required = false) String estado, // RF12
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimientoInicio, // RF11
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimientoFin) { // RF11

        if (estado != null && !estado.isEmpty()) {
            // RF12: Filtro por estado
            return new ResponseEntity<>(pacienteService.obtenerPacientesPorEstado(estado), HttpStatus.OK);
        } else if (fechaNacimientoInicio != null && fechaNacimientoFin != null) {
            // RF11: Filtro por rango de fechas de nacimiento
            return new ResponseEntity<>(pacienteService.obtenerPacientesPorRangoNacimiento(fechaNacimientoInicio, fechaNacimientoFin), HttpStatus.OK);
        } else {
            // Si no hay filtros específicos, devuelve todos (como fallback)
            return new ResponseEntity<>(pacienteService.obtenerTodosPacientes(), HttpStatus.OK);
        }
    }
}