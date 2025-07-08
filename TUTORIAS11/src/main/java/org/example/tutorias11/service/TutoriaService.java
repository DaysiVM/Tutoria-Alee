package org.example.tutorias11.service;

import org.example.tutorias11.dto.TutoriaDTO;

import java.util.List;

public interface TutoriaService {
    TutoriaDTO createTutoria(TutoriaDTO dto);
    List<TutoriaDTO> getAll();
    TutoriaDTO getById(Long id);
    TutoriaDTO updateTutoria(Long id, TutoriaDTO dto);
    void deleteTutoria(Long id);
    void registrarEstudiante(Long tutoriaId, String username);
}
