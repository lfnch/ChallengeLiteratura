package com.lfng7.literalura.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "books")

public class LibroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private long idApi;

    @Column(unique = true)
    private String title;

    private boolean copyright;

    private String mediaType;

    private long downloadCount;

}
