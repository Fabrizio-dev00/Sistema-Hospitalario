package com.hospital.hospital_system.security;

import com.hospital.hospital_system.model.Usuario;
import com.hospital.hospital_system.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar el usuario en la base de datos
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        if (!usuario.isActivo()) {
            throw new UsernameNotFoundException("Usuario inactivo: " + username);
        }

        // Mapear los roles del usuario a GrantedAuthorities de Spring Security
        Collection<? extends GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre().toUpperCase())) // Usar el nombre del rol (ADMIN, MEDICO, ASISTENTE)
                .collect(Collectors.toList());

        // Devolver un objeto UserDetails que Spring Security pueda entender
        return new User(
                usuario.getUsername(),
                usuario.getPassword(), // Contraseña encriptada
                authorities
        );
    }
}