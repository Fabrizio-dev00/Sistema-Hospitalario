package com.hospital.hospital_system;

import com.hospital.hospital_system.service.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HospitalSystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HospitalSystemApplication.class, args);
    }

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {
        usuarioService.inicializarRoles();
        System.out.println("Roles inicializados correctamente: ADMIN, MEDICO, ASISTENTE.");
    }
}