package co.edu.ue.services;

import org.springframework.stereotype.Service;
import co.edu.ue.model.User;  // Asegúrate de que esta importación sea correcta
import co.edu.ue.repository.UserRepository;

import java.util.Optional;

@Service  // Esta anotación permite que Spring gestione esta clase como un servicio
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public boolean checkCredentials(String username, String password) {
        Optional<User> user = Optional.empty();
        if (user.isPresent()) {
            return passwordEncoder.matches(password, user.get().getPassword());
        }
        return false;
    }
}
