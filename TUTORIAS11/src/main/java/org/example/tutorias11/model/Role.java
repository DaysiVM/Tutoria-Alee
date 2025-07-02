package org.example.tutorias11.model;

import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RolNombre nombreRol;



    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public RolNombre getNombreRol() { return nombreRol; }
    public void setNombreRol(RolNombre nombreRol) { this.nombreRol = nombreRol; }
}
