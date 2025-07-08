package org.example.tutorias11.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tutorias")
public class Tutoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String materia;

    @Column(length = 255, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String hora;

    @Column(nullable = false)
    private Double costo;

    @Column(nullable = false)
    private String nombreTutor;

    @Column(nullable = false)
    private String correoTutor;


    @ManyToMany
    @JoinTable(
            name = "tutoria_estudiantes",
            joinColumns = @JoinColumn(name = "tutoria_id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id")
    )
    private Set<Usuario> estudiantes = new HashSet<>();

    // === Getters & Setters ===
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public Double getCosto() { return costo; }
    public void setCosto(Double costo) { this.costo = costo; }

    public String getNombreTutor() { return nombreTutor; }
    public void setNombreTutor(String nombreTutor) { this.nombreTutor = nombreTutor; }

    public String getCorreoTutor() { return correoTutor; }
    public void setCorreoTutor(String correoTutor) { this.correoTutor = correoTutor; }

    public Set<Usuario> getEstudiantes() { return estudiantes; }
    public void setEstudiantes(Set<Usuario> estudiantes) { this.estudiantes = estudiantes; }

    public void addEstudiante(Usuario estudiante) {
        this.estudiantes.add(estudiante);
    }

    public void removeEstudiante(Usuario estudiante) {
        this.estudiantes.remove(estudiante);
    }
}