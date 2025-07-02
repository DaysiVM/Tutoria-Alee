package org.example.tutorias11.controller;

import jakarta.validation.Valid;
import org.example.tutorias11.dto.TutoriaDTO;
import org.example.tutorias11.service.TutoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tutorias")
@Validated
public class TutoriaController {

    @Autowired
    private TutoriaService service;

    // Crear una tutoría
    @PostMapping
    public ResponseEntity<TutoriaDTO> create(@Valid @RequestBody TutoriaDTO dto) {
        return new ResponseEntity<>(service.createTutoria(dto), HttpStatus.CREATED);
    }

    // Obtener todas las tutorías
    @GetMapping
    public List<TutoriaDTO> getAll() {
        return service.getAll();
    }

    // Obtener una tutoría por su ID
    @GetMapping("/{id}")
    public ResponseEntity<TutoriaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // Actualizar una tutoría
    @PutMapping("/{id}")
    public ResponseEntity<TutoriaDTO> update(@PathVariable Long id, @Valid @RequestBody TutoriaDTO dto) {
        return ResponseEntity.ok(service.updateTutoria(id, dto));
    }

    // Eliminar una tutoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteTutoria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}