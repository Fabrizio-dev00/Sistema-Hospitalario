package com.hospital.hospital_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "antecedente_medico")
public class AntecedenteMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAntecedente;

    private String tipo;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_historia", referencedColumnName = "idHistoria")
    private HistoriaClinica historiaClinica;
}