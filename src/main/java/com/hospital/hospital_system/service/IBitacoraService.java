package com.hospital.hospital_system.service;

import com.hospital.hospital_system.model.Bitacora;

import java.util.List;

public interface IBitacoraService {

    /**
     * RF20 / RNF3: Registra una operación en la Bitácora.
     * @param usuario Nombre de usuario que realiza la acción (o "SISTEMA").
     * @param operacion Tipo de acción (e.g., CREACION, ACTUALIZACION, ACCESO_FALLIDO).
     * @param detalles Descripción de la acción (e.g., "Paciente ID 5 registrado").
     */
    void registrarAccion(String usuario, String operacion, String detalles);

    List<Bitacora> obtenerTodosLosRegistros();
}