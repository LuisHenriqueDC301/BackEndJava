package com.zion.api.Controller;

import com.zion.api.Service.UserService;
import com.zion.api.domain.User.LoginRequestDTO;
import com.zion.api.domain.User.LoginResponseDTO;
import com.zion.api.domain.User.User;
import com.zion.api.domain.User.UserRequestDTO;
import com.zion.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repository;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody UserRequestDTO body) {
        User user = this.userService.createUser(body);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO body) {
        String token = userService.authenticateUser(body.email(), body.password());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile() {
        // Obtém o e-mail do usuário autenticado a partir do contexto de segurança
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Busca os dados do usuário no banco de dados
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return ResponseEntity.ok(user);
    }
}