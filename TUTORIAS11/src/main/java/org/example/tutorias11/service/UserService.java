package org.example.tutorias11.service;


import org.example.tutorias11.model.Usuario;
import org.example.tutorias11.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean authenticate(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username).orElse(null);

        if (usuario == null) {
            return false; // Usuario no existe
        }

        // Compara la contraseña hasheada guardada con la que pasa el usuario
        return passwordEncoder.matches(password, usuario.getPassword());
    }
}
