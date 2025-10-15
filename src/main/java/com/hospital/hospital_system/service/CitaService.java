package com.hospital.hospital_system.service;

import com.hospital.hospital_system.model.Cita;
import com.hospital.hospital_system.model.Medico;
import com.hospital.hospital_system.model.Paciente;
import com.hospital.hospital_system.repository.CitaRepository;
import com.hospital.hospital_system.repository.MedicoRepository;
import com.hospital.hospital_system.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class CitaService implements ICitaService {

    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    private static final LocalTime HORA_INICIO_LABORAL = LocalTime.of(8, 0);
    private static final LocalTime HORA_FIN_LABORAL = LocalTime.of(18, 0);

    @Override
    public Cita registrarCita(Cita cita) {

        Paciente paciente = pacienteRepository.findById(cita.getPaciente().getIdPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Medico medico = medicoRepository.findById(cita.getMedico().getIdMedico())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        cita.setPaciente(paciente);
        cita.setMedico(medico);

        validarHorarioLaboral(cita.getFechaHora());

        validarCitaDuplicada(cita.getMedico(), cita.getPaciente(), cita.getFechaHora());

        return citaRepository.save(cita);
    }

    @Override
    public List<Cita> obtenerTodasCitas() {
        return citaRepository.findAll();
    }

    @Override
    public List<Cita> buscarCitas(Integer idMedico, Integer idPaciente,
                                  LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado) {

        List<Cita> citas = citaRepository.findAll();

        return citas.stream()
                .filter(c -> estado == null || c.getEstado().equalsIgnoreCase(estado))

                .filter(c -> idMedico == null || (c.getMedico() != null && c.getMedico().getIdMedico().equals(idMedico)))

                .filter(c -> idPaciente == null || (c.getPaciente() != null && c.getPaciente().getIdPaciente().equals(idPaciente)))

                .filter(c -> {
                    LocalDateTime citaFecha = c.getFechaHora();
                    boolean pasaInicio = fechaInicio == null || !citaFecha.isBefore(fechaInicio);
                    boolean pasaFin = fechaFin == null || citaFecha.isBefore(fechaFin);
                    return pasaInicio && pasaFin;
                })
                .toList();
    }

    private void validarHorarioLaboral(LocalDateTime fechaHora) {
        DayOfWeek dia = fechaHora.getDayOfWeek();
        LocalTime hora = fechaHora.toLocalTime();

        if (dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY) {
            throw new RuntimeException("RF5: No se pueden registrar citas los fines de semana.");
        }

        if (hora.isBefore(HORA_INICIO_LABORAL) || hora.isAfter(HORA_FIN_LABORAL) || hora.equals(HORA_FIN_LABORAL)) {
            throw new RuntimeException("RF5: La cita debe estar dentro del horario laboral (8:00 AM - 6:00 PM).");
        }
    }

    private void validarCitaDuplicada(Medico medico, Paciente paciente, LocalDateTime fechaHora) {
        if (citaRepository.findByMedicoAndFechaHora(medico, fechaHora).isPresent()) {
            throw new RuntimeException("RF6: El médico ya tiene una cita programada para esa hora.");
        }

        if (citaRepository.findByPacienteAndFechaHora(paciente, fechaHora).isPresent()) {
            throw new RuntimeException("RF6: El paciente ya tiene una cita programada para esa hora.");
        }
    }
}