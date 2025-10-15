package com.hospital.hospital_system.service;

import com.hospital.hospital_system.model.Medico;
import com.hospital.hospital_system.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService implements IMedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public Medico guardarMedico(Medico medico) {
        medico.setEstado("activo");
        return medicoRepository.save(medico);
    }

    @Override
    public List<Medico> obtenerTodosMedicos() {
        return medicoRepository.findAll();
    }

    @Override
    public Optional<Medico> obtenerMedicoPorId(Integer id) {
        return medicoRepository.findById(id);
    }

    @Override
    public Medico actualizarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    @Override
    public void desactivarMedico(Integer id) {
        Optional<Medico> optionalMedico = medicoRepository.findById(id);
        if (optionalMedico.isPresent()) {
            Medico medico = optionalMedico.get();
            medico.setEstado("inactivo");
            medicoRepository.save(medico);
        }
    }

    @Override
    public List<Medico> buscarPorEspecialidad(String especialidad, String estado) {
        return List.of();
    }

    @Override
    public List<Medico> buscarPorEspecialidad(String especialidad) {
        return List.of();
    }

    @Override
    public List<Medico> buscarPorEspecialidadYEstado(String especialidad, String estado) {
        // Obtenemos todos y filtramos con streams
        List<Medico> medicos = medicoRepository.findAll();

        return medicos.stream()
                // RF13: Filtrar por especialidad (case insensitive)
                .filter(m -> especialidad == null || m.getEspecialidad().equalsIgnoreCase(especialidad))
                // RF18: Filtrar por estado
                .filter(m -> estado == null || m.getEstado().equalsIgnoreCase(estado))
                .toList();
    }
}