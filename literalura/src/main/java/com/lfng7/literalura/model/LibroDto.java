package com.lfng7.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;
import java.util.Map;

public record LibroDto(
        @JsonAlias("id") long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("subjects") List<String> subjectos,
        @JsonAlias("authors") List<PersonaDto> autores,
        @JsonAlias("translators") List<PersonaDto> traductores,
        @JsonAlias("bookshelves") List<String> estanterias,
        @JsonAlias("languages") List<String> lenguajes,
        @JsonAlias("copyright") boolean derechosAudor,
        @JsonAlias("media_type") String tipoMedio,
        @JsonAlias("formats") Map<String, String> formatos,
        @JsonAlias("download_count") long cuentaDescargas
) {
}
