package com.hospital.hospital_system.service;

import com.hospital.hospital_system.model.Medico;
import java.util.List;
import java.util.Optional;

public interface IMedicoService {

    Medico guardarMedico(Medico medico);
    List<Medico> obtenerTodosMedicos();
    Optional<Medico> obtenerMedicoPorId(Integer id);
    Medico actualizarMedico(Medico medico);
    void desactivarMedico(Integer id);

    List<Medico> buscarPorEspecialidad(String especialidad, String estado);

    List<Medico> buscarPorEspecialidad(String especialidad);

    List<Medico> buscarPorEspecialidadYEstado(String especialidad, String estado);
}