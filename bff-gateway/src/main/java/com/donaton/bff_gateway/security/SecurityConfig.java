package com.donaton.bff_gateway.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    private final JwtValidatorFilter jwtValidatorFilter;

    // Inyección explícita por constructor (Evitamos fallos con Lombok en filtros de Spring)
    public SecurityConfig(JwtValidatorFilter jwtValidatorFilter) {
        this.jwtValidatorFilter = jwtValidatorFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                // Activamos CORS con las especificaciones del Bean de abajo
                .cors(Customizer.withDefaults())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/gateway/usuarios/login",
                                "/gateway/usuarios/registro",
                                "/gateway/centros/**",
                                "/gateway/donaciones/**",
                                "/gateway/necesidades/**",
                                "/actuator/**",
                                "/error" // Desbloquea respuestas reales del backend en lugar de mostrar un 403 genérico
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtValidatorFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Configuración CORS robusta para permitir peticiones desde React (puerto 5173)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); 
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}