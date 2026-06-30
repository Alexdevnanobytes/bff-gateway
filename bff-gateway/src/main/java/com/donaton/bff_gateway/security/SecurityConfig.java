package com.donaton.bff_gateway.security;

<<<<<<< HEAD
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
=======
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
>>>>>>> b72499bb6489d2c1f2568c89e7f2bd8660ad7b6f
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

<<<<<<< HEAD
import java.util.Arrays;
import java.util.List;

@Configuration
=======
@Configuration
@RequiredArgsConstructor
>>>>>>> b72499bb6489d2c1f2568c89e7f2bd8660ad7b6f
public class SecurityConfig {

    private final JwtValidatorFilter jwtValidatorFilter;

<<<<<<< HEAD
    public SecurityConfig(JwtValidatorFilter jwtValidatorFilter) {
        this.jwtValidatorFilter = jwtValidatorFilter;
    }

=======
>>>>>>> b72499bb6489d2c1f2568c89e7f2bd8660ad7b6f
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
<<<<<<< HEAD
=======
                // Activamos CORS para que React (puerto 5173) pueda entrar
>>>>>>> b72499bb6489d2c1f2568c89e7f2bd8660ad7b6f
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
<<<<<<< HEAD
                                "/gateway/centros/**",
                                "/gateway/donaciones/**",
                                "/gateway/necesidades/**",
                                "/actuator/**",
                                "/error"
=======
                                "/gateway/centros/**", // CORREGIDO: Eliminado "/usuarios" para que coincida con el Controller
                                "/gateway/donaciones/**",
                                "/gateway/necesidades/**",
                                "/actuator/**"
>>>>>>> b72499bb6489d2c1f2568c89e7f2bd8660ad7b6f
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtValidatorFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
<<<<<<< HEAD

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
=======
>>>>>>> b72499bb6489d2c1f2568c89e7f2bd8660ad7b6f
}