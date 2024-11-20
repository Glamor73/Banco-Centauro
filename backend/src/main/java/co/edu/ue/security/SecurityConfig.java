package co.edu.ue.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {	

    @Bean
    public InMemoryUserDetailsManager userManager() {
        // Aquí creas los usuarios con sus roles
        List<UserDetails> users = List.of(
            User.withUsername("HOLA").password("{noop}1234").roles("USER").build(),
            User.withUsername("1234").password("{noop}1234").roles("ADMIN").build()
        );
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(cus -> cus.disable()) // Desactiva la protección CSRF
            .authorizeHttpRequests(aut -> 
                // Configuración de rutas y roles permitidos
                aut.requestMatchers(HttpMethod.POST, "/auth/register").permitAll() // Permite registro sin autenticación
                   .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Permite login sin autenticación
                   .requestMatchers(HttpMethod.GET, "/api/pets").hasRole("USER") // Permite acceso a /api/pets solo a usuarios con rol USER
                   .requestMatchers(HttpMethod.POST, "/api/pets").hasRole("ADMIN") // Solo ADMIN puede crear nuevos pets
                   .requestMatchers(HttpMethod.PUT, "/api/pets").hasRole("ADMIN") // Solo ADMIN puede actualizar los pets
                   .requestMatchers(HttpMethod.DELETE, "/api/pets").hasRole("ADMIN") // Solo ADMIN puede eliminar los pets
                   .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
            )
            .httpBasic(Customizer.withDefaults()); // Usamos autenticación básica
        return http.build();
    }
}
