package org.example.tutorias11.controller;

import org.example.tutorias11.dto.AuthRequest;
import org.example.tutorias11.dto.AuthResponse;
import org.example.tutorias11.model.Usuario;
import org.example.tutorias11.repository.UsuarioRepository;
import org.example.tutorias11.config.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JWTUtil jwtUtil;

    public AuthController(UsuarioRepository usuarioRepository, JWTUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        // 1️⃣ Busca el usuario en la base de datos
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElse(null);

        // 2️⃣ Valida que exista y que la contraseña coincida
        if (usuario != null && usuario.getPassword().equals(request.getPassword())) {


            List<String> roles = usuario.getRoles().stream()
                    .map(r -> r.getNombreRol().name())
                    .collect(Collectors.toList());

            // 4️⃣ Genera el token con roles
            String token = jwtUtil.generateToken(usuario.getUsername(), roles);

            // 5️⃣ Devuelve la respuesta con el token
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
