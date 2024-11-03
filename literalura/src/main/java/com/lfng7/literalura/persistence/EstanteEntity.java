package com.lfng7.literalura.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "bookshelves")

public class EstanteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String bookshelf;
}
