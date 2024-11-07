package com.lfng7.literalura.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "formats")
public class FormatoEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "format", unique = true, nullable = false)
    private String format;

    @ManyToMany(mappedBy = "formatos")
    private List<LibroEntity> libros;

    public FormatoEntity() {
    }

    public FormatoEntity(String format) {
        this.format = format;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<LibroEntity> getLibros() {
        return libros;
    }

    public void setLibros(List<LibroEntity> libros) {
        this.libros = libros;
    }
}
