package com.lfng7.literalura.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "subjects")

public class SubjectoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String subject;
}
