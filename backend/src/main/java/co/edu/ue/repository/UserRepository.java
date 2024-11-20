package co.edu.ue.repository;

import co.edu.ue.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Busca un usuario por su nombre de usuario. Optional es mejor para evitar nulls
    Optional<User> findByUsername(String username);

    // Si quieres más consultas personalizadas, puedes agregar aquí
    // Ejemplo: Buscar por correo electrónico
    Optional<User> findByEmail(String email);

    // Ejemplo: Buscar si existe un usuario con un nombre de usuario específico
    boolean existsByUsername(String username);
    
    // Ejemplo: Buscar si existe un usuario con un correo electrónico específico
    boolean existsByEmail(String email);
}
