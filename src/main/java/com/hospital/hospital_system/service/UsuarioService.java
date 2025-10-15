package com.hospital.hospital_system.service;

import com.hospital.hospital_system.model.Rol;
import com.hospital.hospital_system.model.Usuario;
import com.hospital.hospital_system.repository.RolRepository;
import com.hospital.hospital_system.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private IBitacoraService bitacoraService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String ROL_ADMIN = "ADMIN";
    private static final String ROL_MEDICO = "MEDICO";
    private static final String ROL_ASISTENTE = "ASISTENTE";


    @Override
    @Transactional
    public Usuario registrarUsuario(Usuario usuario, String nombreRol) {

        Rol rol = rolRepository.findByNombre(nombreRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + nombreRol));

        Set<Rol> roles = new HashSet<>();
        roles.add(rol);
        usuario.setRoles(roles);

        String passwordEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordEncriptada);

        Usuario nuevoUsuario = usuarioRepository.save(usuario);

        String detalles = String.format("Usuario '%s' registrado con ID %d y Rol: %s",
                usuario.getUsername(), nuevoUsuario.getIdUsuario(), nombreRol);
        bitacoraService.registrarAccion("SISTEMA/ADMIN", "CREACION_USUARIO", detalles);

        return nuevoUsuario;
    }

    @Override
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public void cambiarEstadoUsuario(Integer idUsuario, boolean activo) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setActivo(activo);
            usuarioRepository.save(usuario);

            String estado = activo ? "ACTIVADO" : "DESACTIVADO";
            String detalles = String.format("Estado de Usuario '%s' ID %d cambiado a %s.",
                    usuario.getUsername(), idUsuario, estado);
            bitacoraService.registrarAccion("SISTEMA/ADMIN", "ACTUALIZACION_USUARIO", detalles);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + idUsuario);
        }
    }

    @Override
    @Transactional
    public void inicializarRoles() {
        if (rolRepository.findByNombre(ROL_ADMIN).isEmpty()) {
            rolRepository.save(new Rol(null, ROL_ADMIN, "Administrador del Sistema"));
        }
        if (rolRepository.findByNombre(ROL_MEDICO).isEmpty()) {
            rolRepository.save(new Rol(null, ROL_MEDICO, "Médico Asistencial"));
        }
        if (rolRepository.findByNombre(ROL_ASISTENTE).isEmpty()) {
            rolRepository.save(new Rol(null, ROL_ASISTENTE, "Asistente de Citas y Administración"));
        }
    }
}
