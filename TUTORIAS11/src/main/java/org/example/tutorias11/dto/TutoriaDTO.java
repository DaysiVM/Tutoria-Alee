package org.example.tutorias11.dto;

import jakarta.validation.constraints.*;

public class TutoriaDTO {

    private Long id;

    @NotBlank(message = "La materia no puede estar vacía")
    private String materia;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no debe exceder 255 caracteres")
    private String descripcion;

    @NotBlank(message = "La hora no puede estar vacía")
    private String hora;

    @NotNull(message = "El costo no puede ser nulo")
    private Double costo;

    @NotBlank(message = "El nombre del tutor no puede estar vacío")
    private String nombreTutor;

    @NotBlank(message = "El correo del tutor no puede estar vacío")
    @Email(message = "El correo del tutor no es válido")
    private String correoTutor;

    // ✅ Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {  // 👈 El setter que faltaba
        this.id = id;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public String getCorreoTutor() {
        return correoTutor;
    }

    public void setCorreoTutor(String correoTutor) {
        this.correoTutor = correoTutor;
    }
}
