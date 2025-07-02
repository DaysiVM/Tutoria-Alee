package org.example.tutorias11.mapper;

import org.example.tutorias11.dto.TutoriaDTO;
import org.example.tutorias11.model.Tutoria;
import org.springframework.stereotype.Component;

@Component
public class TutoriaMapper {

    public Tutoria toEntity(TutoriaDTO dto) {
        Tutoria tutoria = new Tutoria();
        tutoria.setMateria(dto.getMateria());
        tutoria.setDescripcion(dto.getDescripcion());
        tutoria.setHora(dto.getHora());
        tutoria.setCosto(dto.getCosto());
        tutoria.setNombreTutor(dto.getNombreTutor());
        tutoria.setCorreoTutor(dto.getCorreoTutor());
        return tutoria;
    }

    public TutoriaDTO toDTO(Tutoria entity) {
        TutoriaDTO dto = new TutoriaDTO();
        dto.setId(entity.getId()); // 👈 Clave: ahora copia el ID generado
        dto.setMateria(entity.getMateria());
        dto.setDescripcion(entity.getDescripcion());
        dto.setHora(entity.getHora());
        dto.setCosto(entity.getCosto());
        dto.setNombreTutor(entity.getNombreTutor());
        dto.setCorreoTutor(entity.getCorreoTutor());
        return dto;
    }
}
