package com.hospital.hospital_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "historia_clinica")
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistoria;

    @Column(name = "fecha_apertura")
    private LocalDate fechaApertura;

    private String observaciones;


    @OneToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "idPaciente", unique = true)
    private Paciente paciente;
}