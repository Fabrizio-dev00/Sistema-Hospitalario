package com.hospital.hospital_system.repository;

import com.hospital.hospital_system.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    List<Paciente> findByEstado(String estado);

    List<Paciente> findByFechaNacimientoBetween(LocalDate fechaInicio, LocalDate fechaFin);
}