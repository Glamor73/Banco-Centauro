package co.edu.ue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SuppressWarnings("unused")
    private String username;
    @SuppressWarnings("unused")
    private String password;
    public void setUsername(String username2) {
        throw new UnsupportedOperationException("Unimplemented method 'setUsername'");
    }
    public void setPassword(String hashedPassword) {
        throw new UnsupportedOperationException("Unimplemented method 'setPassword'");
    }
    public String getPassword() {
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    // Getters y Setters
}
