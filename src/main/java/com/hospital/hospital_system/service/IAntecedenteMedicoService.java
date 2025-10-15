package com.hospital.hospital_system.service;

import com.hospital.hospital_system.model.AntecedenteMedico;
import com.hospital.hospital_system.model.HistoriaClinica;

public interface IAntecedenteMedicoService {

    /**
     * RF3: Registra un nuevo antecedente médico y lo asocia a una Historia Clínica.
     * @param antecedente El objeto AntecedenteMedico con el tipo y la descripción.
     * @param idHistoria El ID de la Historia Clínica a la que se vinculará.
     * @return El AntecedenteMedico guardado.
     * @throws RuntimeException si la Historia Clínica no existe.
     */
    AntecedenteMedico registrarAntecedente(AntecedenteMedico antecedente, Integer idHistoria);
}