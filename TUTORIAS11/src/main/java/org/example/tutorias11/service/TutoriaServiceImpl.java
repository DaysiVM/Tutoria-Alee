package org.example.tutorias11.service;

import org.example.tutorias11.dto.TutoriaDTO;
import org.example.tutorias11.exception.ResourceNotFoundException;
import org.example.tutorias11.mapper.TutoriaMapper;
import org.example.tutorias11.model.Tutoria;
import org.example.tutorias11.model.Usuario;
import org.example.tutorias11.repository.TutoriaRepository;
import org.example.tutorias11.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutoriaServiceImpl implements TutoriaService {

    private final TutoriaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final TutoriaMapper mapper;

    public TutoriaServiceImpl(TutoriaRepository repository, UsuarioRepository usuarioRepository, TutoriaMapper mapper) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    @Override
    public TutoriaDTO createTutoria(TutoriaDTO dto) {
        Tutoria entity = mapper.toEntity(dto);
        Tutoria saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public List<TutoriaDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TutoriaDTO getById(Long id) {
        Tutoria tutoria = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutoria no encontrada con id " + id));
        return mapper.toDTO(tutoria);
    }

    @Override
    public TutoriaDTO updateTutoria(Long id, TutoriaDTO dto) {
        Tutoria tutoria = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutoria no encontrada con id " + id));
        // Actualizar campos
        tutoria.setMateria(dto.getMateria());
        tutoria.setDescripcion(dto.getDescripcion());
        tutoria.setHora(dto.getHora());
        tutoria.setCosto(dto.getCosto());
        tutoria.setNombreTutor(dto.getNombreTutor());
        tutoria.setCorreoTutor(dto.getCorreoTutor());

        Tutoria updated = repository.save(tutoria);
        return mapper.toDTO(updated);
    }

    @Override
    public void deleteTutoria(Long id) {
        Tutoria tutoria = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutoria no encontrada con id " + id));
        repository.delete(tutoria);
    }

    @Override
    public void registrarEstudiante(Long tutoriaId, String username) {
        Tutoria tutoria = repository.findById(tutoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Tutoria no encontrada con id " + tutoriaId));

        Usuario estudiante = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + username));


        tutoria.getEstudiantes().add(estudiante);

        repository.save(tutoria);
    }
}
