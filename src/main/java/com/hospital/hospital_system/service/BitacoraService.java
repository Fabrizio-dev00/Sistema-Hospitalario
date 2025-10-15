package com.hospital.hospital_system.service;

import com.hospital.hospital_system.model.Bitacora;
import com.hospital.hospital_system.repository.BitacoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BitacoraService implements IBitacoraService {

    @Autowired
    private BitacoraRepository bitacoraRepository;

    @Override
    public void registrarAccion(String usuario, String operacion, String detalles) {
        Bitacora log = new Bitacora();
        log.setUsuario(usuario);
        log.setOperacion(operacion);
        log.setDetalles(detalles);
        log.setFechaHora(LocalDateTime.now());

        bitacoraRepository.save(log);
    }

    @Override
    public List<Bitacora> obtenerTodosLosRegistros() {
        return bitacoraRepository.findAll();
    }
}