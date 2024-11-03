package com.lfng7.literalura.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "persons")

public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int birthYear;
    private int deathYear;
}
