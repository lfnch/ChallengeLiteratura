package com.lfng7.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record PersonaRecord(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") int fechaNacio,
        @JsonAlias("death_year") int fechaMurio
) {
}
