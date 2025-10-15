package com.hospital.hospital_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCita;

    @NotNull(message = "La fecha y hora son obligatorias")
    private LocalDateTime fechaHora;

    @NotNull(message = "El motivo de la cita es obligatorio")
    private String motivo;

    private String estado = "PENDIENTE";

    @ManyToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "idPaciente")
    @NotNull(message = "La cita debe estar asociada a un paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico", referencedColumnName = "idMedico")
    @NotNull(message = "La cita debe estar asociada a un médico")
    private Medico medico;
}