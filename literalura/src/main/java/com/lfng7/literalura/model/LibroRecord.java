package com.lfng7.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;
import java.util.Map;

public record LibroRecord(
        @JsonAlias("id") long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("subjects") List<String> subjectos,
        @JsonAlias("authors") List<PersonaRecord> autores,
        @JsonAlias("translators") List<PersonaRecord> traductores,
        @JsonAlias("bookshelves") List<String> estanterias,
        @JsonAlias("languages") List<String> lenguajes,
        @JsonAlias("copyright") boolean derechosAudor,
        @JsonAlias("media_type") String tipoMedio,
        @JsonAlias("formats") Map<String, String> formatos,
        @JsonAlias("download_count") long cuentaDescargas
) {
}
