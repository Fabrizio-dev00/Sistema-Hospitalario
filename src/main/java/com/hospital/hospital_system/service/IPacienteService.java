package com.hospital.hospital_system.service;

import com.hospital.hospital_system.model.Paciente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPacienteService {

    Paciente guardarPaciente(Paciente paciente);

    List<Paciente> obtenerTodosPacientes();

    Optional<Paciente> obtenerPacientePorId(Integer id);

    Paciente actualizarPaciente(Paciente paciente);

    void desactivarPaciente(Integer id);

    List<Paciente> obtenerPacientesPorEstado(String estado);

    List<Paciente> obtenerPacientesPorRangoNacimiento(LocalDate fechaInicio, LocalDate fechaFin);
}