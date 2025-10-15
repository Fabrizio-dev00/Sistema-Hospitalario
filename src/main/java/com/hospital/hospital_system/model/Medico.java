package com.hospital.hospital_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedico;

    @NotBlank(message = "El DNI es obligatorio")
    @Column(unique = true, length = 8)
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(length = 50)
    private String nombres;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(length = 50)
    private String apellidos;

    @NotBlank(message = "La especialidad es obligatoria")
    @Column(length = 50)
    private String especialidad;

    @Column(length = 15)
    private String telefono;

    @Column(length = 10)
    private String estado = "activo";
}