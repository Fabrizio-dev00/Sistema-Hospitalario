package com.hospital.hospital_system.controller;

import com.hospital.hospital_system.model.Usuario;
import com.hospital.hospital_system.service.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority; // Importación necesaria para el nuevo método
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    // --- MÉTODOS EXISTENTES ---

    @PostMapping
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody Usuario usuario,
                                              @RequestParam String rol) {
        try {
            Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario, rol.toUpperCase());
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        return new ResponseEntity<>(usuarioService.obtenerTodosUsuarios(), HttpStatus.OK);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer id, @RequestParam boolean activo) {
        try {
            usuarioService.cambiarEstadoUsuario(id, activo);
            return new ResponseEntity<>("Estado actualizado correctamente.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint para obtener el nombre de usuario y los roles del usuario autenticado.
     * Es crucial para que el frontend pueda configurar el menú y las vistas.
     */
    @GetMapping("/current-user-roles")
    public ResponseEntity<Map<String, Object>> getCurrentUserRoles() {
        // Obtiene el objeto de autenticación actual de Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 1. Verificar si hay autenticación válida
        // Nota: Spring Security generalmente ya bloquea esto, pero es una buena práctica.
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            Map<String, Object> response = new HashMap<>();
            response.put("username", null);
            response.put("roles", List.of());
            return ResponseEntity.ok(response);
        }

        // 2. Obtener el nombre de usuario de forma segura
        // authentication.getName() es el método estándar para obtener el nombre de usuario
        String username = authentication.getName();

        // 3. Mapea las autoridades (roles) a una lista de Strings
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                // Opcional: Si los roles tienen el prefijo "ROLE_", se elimina para el frontend.
                .map(authority -> authority.replace("ROLE_", ""))
                .collect(Collectors.toList());

        // 4. Construir la respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("roles", roles);

        // Usamos ResponseEntity para consistencia con el resto de los controladores
        return ResponseEntity.ok(response);
    }
}
