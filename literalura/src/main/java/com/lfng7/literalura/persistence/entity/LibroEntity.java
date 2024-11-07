package com.lfng7.literalura.persistence.entity;

import com.lfng7.literalura.model.LibroDto;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")

public class LibroEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_api", unique = true, nullable = false)
    private long idApi;

    @Column(name = "title", length = 500, unique = true, nullable = false)
    private String title;

    @Column(name = "copyright", nullable = true)
    private boolean copyright;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "download_count")
    private long downloadCount;

    /*
    * Relación muchos a muchos
    */

    @ManyToMany
    @JoinTable(name = "books_autors",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_autor"))
    private List<AutorEntity> autores;

    @ManyToMany
    @JoinTable(name = "books_translators",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_translator"))
    private List<TraductorEntity> traductores;

    @ManyToMany
    @JoinTable(name = "books_bookshelves",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_bookshelf"))
    private List<EstanteEntity> estanterias;

    @ManyToMany
    @JoinTable(name = "books_languages",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_language"))
    private List<LenguajeEntity> lenguajes;


    @ManyToMany
    @JoinTable(name = "books_subjects",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_subjects"))
    private List<SubjectoEntity> subjectos;

    @ManyToMany
    @JoinTable(name = "books_formats",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_format"))
    private List<FormatoEntity> formatos;

    public LibroEntity() {
    }

    public LibroEntity(LibroDto libroDto) {
        this.idApi = libroDto.id();
        this.title = libroDto.titulo();
        this.copyright = libroDto.derechosAudor();
        this.mediaType = libroDto.tipoMedio();
        this.downloadCount = libroDto.cuentaDescargas();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdApi() {
        return idApi;
    }

    public void setIdApi(long idApi) {
        this.idApi = idApi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCopyright() {
        return copyright;
    }

    public void setCopyright(boolean copyright) {
        this.copyright = copyright;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public long getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(long downloadCount) {
        this.downloadCount = downloadCount;
    }


    public List<AutorEntity> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorEntity> autores) {
        this.autores = autores;
    }

    public List<TraductorEntity> getTraductores() {
        return traductores;
    }

    public void setTraductores(List<TraductorEntity> traductores) {
        this.traductores = traductores;
    }

    public List<EstanteEntity> getEstanterias() {
        return estanterias;
    }

    public void setEstanterias(List<EstanteEntity> estanterias) {
        this.estanterias = estanterias;
    }

    public List<LenguajeEntity> getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(List<LenguajeEntity> lenguajes) {
        this.lenguajes = lenguajes;
    }

    public List<SubjectoEntity> getSubjectos() {
        return subjectos;
    }

    public void setSubjectos(List<SubjectoEntity> subjectos) {
        this.subjectos = subjectos;
    }

    public List<FormatoEntity> getFormatos() {
        return formatos;
    }

    public void setFormatos(List<FormatoEntity> formatos) {
        this.formatos = formatos;
    }
}
