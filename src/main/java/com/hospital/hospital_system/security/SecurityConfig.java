package com.hospital.hospital_system.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 🔓 SIN CIFRADO (solo para pruebas)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // Manejador de éxito
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write("Login exitoso");
            response.getWriter().flush();
        };
    }

    // Manejador de fallo
    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return (request, response, exception) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Usuario o contraseña incorrectos");
            response.getWriter().flush();
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))

                .formLogin(form -> form
                        .loginProcessingUrl("/api/login")
                        .permitAll()
                        .successHandler(successHandler())
                        .failureHandler(failureHandler())
                )
                .logout(logout -> logout.permitAll())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html", "/static/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers("/api/usuarios/current-user-roles").authenticated()
                        .requestMatchers("/api/usuarios/**", "/api/bitacoras/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/medicos/**").hasAnyAuthority("ADMIN", "ASISTENTE")
                        .requestMatchers("/api/pacientes/reporte").hasAnyAuthority("ADMIN", "ASISTENTE", "MEDICO")
                        .requestMatchers("/api/pacientes", "/api/pacientes/**").hasAnyAuthority("ADMIN", "ASISTENTE")
                        .requestMatchers("/api/citas/**").hasAnyAuthority("ADMIN", "ASISTENTE")
                        .requestMatchers(HttpMethod.GET, "/api/historias/**").hasAnyAuthority("ADMIN", "ASISTENTE", "MEDICO")
                        .requestMatchers("/api/historias/**").hasAnyAuthority("ADMIN", "ASISTENTE")
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
