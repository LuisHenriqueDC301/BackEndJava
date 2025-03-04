package com.zion.api.Service;

import com.zion.api.domain.User.User;
import com.zion.api.domain.User.UserRequestDTO;
import com.zion.api.repositories.UserRepository;
import com.zion.api.Security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, JwtService jwtService) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User createUser(UserRequestDTO data) {
        User user = new User();
        user.setName(data.name());
        user.setEmail(data.email());
        user.setPassword(passwordEncoder.encode(data.password()));

        return repository.save(user);
    }

    public String login(String email, String password) {
        Optional<User> userOpt = repository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return jwtService.generateToken(user.getEmail());
            }
        }
        return null; // Usuário ou senha inválidos
    }

    public String authenticateUser(String email, String password) {
        // Verificar se o usuário existe
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verificar a senha
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Senha incorreta");
        }

        // Gerar token JWT
        return jwtService.generateToken(user.getEmail());
    }
}
