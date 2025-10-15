package com.hospital.hospital_system.service;

import com.hospital.hospital_system.model.AntecedenteMedico;
import com.hospital.hospital_system.model.HistoriaClinica;
import com.hospital.hospital_system.repository.AntecedenteMedicoRepository;
import com.hospital.hospital_system.repository.HistoriaClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AntecedenteMedicoService implements IAntecedenteMedicoService {

    @Autowired
    private AntecedenteMedicoRepository antecedenteMedicoRepository;

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Override
    public AntecedenteMedico registrarAntecedente(AntecedenteMedico antecedente, Integer idHistoria) {
        HistoriaClinica historia = historiaClinicaRepository.findById(idHistoria)
                .orElseThrow(() -> new RuntimeException("Historia Clínica no encontrada con ID: " + idHistoria));

        antecedente.setHistoriaClinica(historia);

        return antecedenteMedicoRepository.save(antecedente);
    }
}