package com.lfng7.literalura.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bookshelves")

public class EstanteEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "bookshelf", unique = true, nullable = false)
    private String bookshelf;

    @ManyToMany(mappedBy = "estanterias")
    private List<LibroEntity> libros;

    public EstanteEntity() {

    }
    public EstanteEntity(String bookshelf) {
        this.bookshelf = bookshelf;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookshelf() {
        return bookshelf;
    }

    public void setBookshelf(String bookshelf) {
        this.bookshelf = bookshelf;
    }
}
