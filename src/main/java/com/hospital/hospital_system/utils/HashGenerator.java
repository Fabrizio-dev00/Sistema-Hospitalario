package com.hospital.hospital_system.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Genera el nuevo hash para 'dr_perez' (contraseña: perez123)
        String perezHash = encoder.encode("perez123");
        System.out.println("Nuevo Hash para perez123: " + perezHash);

        // Genera el nuevo hash para 'admin_general' (contraseña: admin123)
        String adminHash = encoder.encode("admin123");
        System.out.println("Nuevo Hash para admin123: " + adminHash);

        // Repite esto para cualquier otro usuario que genere la advertencia
    }
}
