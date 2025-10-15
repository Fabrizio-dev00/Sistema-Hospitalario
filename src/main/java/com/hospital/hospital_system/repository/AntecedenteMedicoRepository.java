package com.hospital.hospital_system.repository;
import com.hospital.hospital_system.model.AntecedenteMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntecedenteMedicoRepository extends JpaRepository<AntecedenteMedico, Integer> {
}