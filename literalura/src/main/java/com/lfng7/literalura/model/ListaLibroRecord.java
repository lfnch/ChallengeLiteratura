package com.lfng7.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Map;

public record ListaLibroRecord(
        @JsonAlias("count") long cuenta,
        @JsonAlias("next") String siguientePagina,
        @JsonAlias("previous") String anteriorPagina,
        @JsonAlias("results") List<LibroRecord> resultados
) {
}
