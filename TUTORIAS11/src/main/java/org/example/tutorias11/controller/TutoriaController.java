package org.example.tutorias11.controller;

import jakarta.validation.Valid;
import org.example.tutorias11.dto.TutoriaDTO;
import org.example.tutorias11.service.TutoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tutorias")
@Validated
public class TutoriaController {

    @Autowired
    private TutoriaService service;


    @PostMapping
    @PreAuthorize("hasRole('TUTOR')")
    public ResponseEntity<TutoriaDTO> create(@Valid @RequestBody TutoriaDTO dto) {
        return new ResponseEntity<>(service.createTutoria(dto), HttpStatus.CREATED);
    }


    @GetMapping
    @PreAuthorize("hasRole('TUTOR') or hasRole('ESTUDIANTE')")
    public List<TutoriaDTO> getAll() {
        return service.getAll();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('TUTOR') or hasRole('ESTUDIANTE')")
    public ResponseEntity<TutoriaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TUTOR')")
    public ResponseEntity<TutoriaDTO> update(@PathVariable Long id, @Valid @RequestBody TutoriaDTO dto) {
        return ResponseEntity.ok(service.updateTutoria(id, dto));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TUTOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteTutoria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/{id}/registro")
    @PreAuthorize("hasRole('ESTUDIANTE')")
    public ResponseEntity<String> registrarEstudianteEnTutoria(@PathVariable Long id, Principal principal) {
        service.registrarEstudiante(id, principal.getName());
        return ResponseEntity.ok("✅ Registro exitoso a la tutoría " + id);
    }
}