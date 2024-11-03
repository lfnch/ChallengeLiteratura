package com.lfng7.literalura.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "types_person")

public class TipoPersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;
}
