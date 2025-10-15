package com.hospital.hospital_system.service;

import com.hospital.hospital_system.model.Usuario;
import java.util.List;

public interface IUsuarioService {

    Usuario registrarUsuario(Usuario usuario, String nombreRol);
    List<Usuario> obtenerTodosUsuarios();
    void cambiarEstadoUsuario(Integer idUsuario, boolean activo);

    void inicializarRoles();
}