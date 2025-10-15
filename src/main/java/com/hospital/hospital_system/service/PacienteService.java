package com.hospital.hospital_system.service;

import com.hospital.hospital_system.model.Paciente;
import com.hospital.hospital_system.model.HistoriaClinica;
import com.hospital.hospital_system.repository.PacienteRepository;
import com.hospital.hospital_system.repository.HistoriaClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class PacienteService implements IPacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        paciente.setEstado("activo");
        Paciente nuevoPaciente = pacienteRepository.save(paciente);

        HistoriaClinica historia = new HistoriaClinica();
        historia.setPaciente(nuevoPaciente);
        historia.setFechaApertura(java.time.LocalDate.now());
        historia.setObservaciones("Historia Clínica abierta automáticamente al registro.");

        historiaClinicaRepository.save(historia);

        return nuevoPaciente;
    }

    @Override
    public List<Paciente> obtenerTodosPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> obtenerPacientePorId(Integer id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public void desactivarPaciente(Integer id) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        if (optionalPaciente.isPresent()) {
            Paciente paciente = optionalPaciente.get();
            paciente.setEstado("inactivo");
            pacienteRepository.save(paciente);
        }
    }

    @Override
    public List<Paciente> obtenerPacientesPorEstado(String estado) {
        return List.of();
    }

    @Override
    public List<Paciente> obtenerPacientesPorRangoNacimiento(LocalDate fechaInicio, LocalDate fechaFin) {
        return List.of();
    }
}