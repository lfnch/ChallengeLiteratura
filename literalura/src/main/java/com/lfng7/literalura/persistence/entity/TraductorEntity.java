package com.lfng7.literalura.persistence.entity;

import com.lfng7.literalura.model.PersonaDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "translators")
public class TraductorEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "birth_year")
    private int birthYear;

    @Column(name = "death_year")
    private int deathYear;

    @ManyToMany(mappedBy = "traductores")
    private List<LibroEntity> libros;

    public TraductorEntity() {

    }

    public TraductorEntity(PersonaDto persona) {
        this.name = persona.nombre();
        this.birthYear = persona.fechaNacio();;
        this.deathYear = persona.fechaMurio();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    public List<LibroEntity> getLibros() {
        return libros;
    }

    public void setLibros(List<LibroEntity> libros) {
        this.libros = libros;
    }
}