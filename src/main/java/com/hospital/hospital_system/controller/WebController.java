package com.hospital.hospital_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/medicos")
    public String medicos() {
        return "medicos.html";
    }

    @GetMapping("/citas")
    public String citas() {
        return "citas.html";
    }

    @GetMapping("/pacientes")
    public String pacientes() {
        return "pacientes.html";
    }

    @GetMapping("/historias")
    public String historias() {
        return "historias.html";
    }

    @GetMapping("/reportes")
    public String reportes() {
        return "reportes.html";
    }

    @GetMapping("/usuarios")
    public String usuarios() {
        return "usuarios.html";
    }
}
