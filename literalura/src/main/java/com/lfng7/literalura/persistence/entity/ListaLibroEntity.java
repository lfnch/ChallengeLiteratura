package com.lfng7.literalura.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "list_books")

public class ListaLibroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fechaActual;


}
