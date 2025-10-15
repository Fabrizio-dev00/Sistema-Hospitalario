package com.hospital.hospital_system.repository;

import com.hospital.hospital_system.model.Cita;
import com.hospital.hospital_system.model.Medico;
import com.hospital.hospital_system.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    Optional<Cita> findByMedicoAndFechaHora(Medico medico, LocalDateTime fechaHora);

    Optional<Cita> findByPacienteAndFechaHora(Paciente paciente, LocalDateTime fechaHora);

    List<Cita> findByEstado(String estado);

    List<Cita> findByFechaHoraBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    List<Cita> findByFechaHoraBetweenAndEstado(LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado);

    List<Cita> findByMedico(Medico medico);

    List<Cita> findByPaciente(Paciente paciente);
}