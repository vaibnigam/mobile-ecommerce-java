package ecommerce.service;

import ecommerce.exception.UserNotFoundException;
import ecommerce.model.User;
import ecommerce.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        if (user.getEmail() == null || !user.getEmail().matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email.");
        }
        if (user.getPassword() == null || user.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password must contain at least 4 characters.");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already registered.");
        }
        userRepository.save(user);
    }

    public User login(String email, String password) {
        if (email == null || email.isBlank()) {
            throw new UserNotFoundException("Email cannot be empty.");
        }

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("No account found for this email.");
        }

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password.");
        }

        return user;
    }

    public User findById(long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        return user;
    }
}
