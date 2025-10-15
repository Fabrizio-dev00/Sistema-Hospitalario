package com.hospital.hospital_system.service;

import com.hospital.hospital_system.model.Cita;

import java.time.LocalDateTime;
import java.util.List;

public interface ICitaService {

    Cita registrarCita(Cita cita);
    List<Cita> obtenerTodasCitas();
    List<Cita> buscarCitas(Integer idMedico, Integer idPaciente,
                           LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado);
}