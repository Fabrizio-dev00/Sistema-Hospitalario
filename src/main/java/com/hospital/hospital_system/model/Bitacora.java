package com.hospital.hospital_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bitacora")
public class Bitacora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBitacora;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora = LocalDateTime.now();

    private String usuario;
    private String operacion;
    private String detalles;
    private String ip;

    public Bitacora(String usuario, String operacion, String detalles) {
        this.usuario = usuario;
        this.operacion = operacion;
        this.detalles = detalles;
    }
}