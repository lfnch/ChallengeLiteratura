package com.lfng7.literalura.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "formats")

public class FormatoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String format;
}
