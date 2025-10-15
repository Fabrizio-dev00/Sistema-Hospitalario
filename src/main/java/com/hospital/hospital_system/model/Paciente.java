package com.hospital.hospital_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaciente;

    @NotBlank(message = "El DNI es obligatorio")
    @Column(unique = true, length = 8)
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(length = 50)
    private String nombres;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(length = 50)
    private String apellidos;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El sexo es obligatorio")
    @Column(length = 10)
    private String sexo;

    private String direccion;

    @Column(length = 15)
    private String telefono;

    @Email(message = "El correo debe ser válido")
    private String correo;

    @Column(length = 10)
    private String estado = "activo";
}