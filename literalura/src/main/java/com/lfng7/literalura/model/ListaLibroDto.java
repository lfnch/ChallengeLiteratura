package com.lfng7.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record ListaLibroDto(
        @JsonAlias("count") long cuenta,
        @JsonAlias("next") String siguientePagina,
        @JsonAlias("previous") String anteriorPagina,
        @JsonAlias("results") List<LibroDto> resultados
) {
}
