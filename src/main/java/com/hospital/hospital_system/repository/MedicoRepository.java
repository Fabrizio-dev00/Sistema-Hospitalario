package com.hospital.hospital_system.repository;

import com.hospital.hospital_system.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    List<Medico> findByEspecialidad(String especialidad);

    List<Medico> findByEstado(String estado);
}