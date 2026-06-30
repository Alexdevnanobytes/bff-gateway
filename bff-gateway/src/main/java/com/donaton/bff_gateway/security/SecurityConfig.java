package com.donaton.bff_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtValidatorFilter jwtValidatorFilter;

    public SecurityConfig(JwtValidatorFilter jwtValidatorFilter) {
        this.jwtValidatorFilter = jwtValidatorFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
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
                                "/error"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtValidatorFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // 🌟 EL BEAN COMPLEMENTARIO DE BUENA PRÁCTICA QUE FALTA EN LA RAMA LIMPIA 🌟
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Permite la URL de tu entorno de desarrollo en React
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); 
        // Habilita explícitamente los métodos que usa el Dashboard
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Permite las cabeceras necesarias, incluyendo el Token de autenticación
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Aplica esta regla de origen a todas las rutas que entran por el Gateway
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}