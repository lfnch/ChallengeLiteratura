package com.lfng7.literalura.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "languages")
public class LenguajeEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "language", unique = true, nullable = false)
    private String language;

    @ManyToMany(mappedBy = "lenguajes")
    private List<LibroEntity> libros;

    public LenguajeEntity() {

    }

    public LenguajeEntity(String lenguaje) {
        this.language = lenguaje;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<LibroEntity> getLibros() {
        return libros;
    }

    public void setLibros(List<LibroEntity> libros) {
        this.libros = libros;
    }
}
