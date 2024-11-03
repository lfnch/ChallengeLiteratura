package com.lfng7.literalura.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "languages")

public class LenguajeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String language;
}
