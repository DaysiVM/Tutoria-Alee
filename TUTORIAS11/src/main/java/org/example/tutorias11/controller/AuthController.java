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
        // Busca el usuario en la base de datos
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElse(null);

        // Validar usuario
        if (usuario == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Usuario incorrecto");
        }

        // Validar contraseña
        if (!usuario.getPassword().equals(request.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Contraseña incorrecta");
        }

        // Generar roles
        List<String> roles = usuario.getRoles().stream()
                .map(r -> r.getNombreRol().name())
                .collect(Collectors.toList());

        // Generar token
        String token = jwtUtil.generateToken(usuario.getUsername(), roles);

        // Responder con token
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
