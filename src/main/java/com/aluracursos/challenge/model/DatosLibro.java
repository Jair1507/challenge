package com.aluracursos.challenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(@JsonAlias("title") String titulo,
                         @JsonAlias("authors") List<DatosAutor> autor,
                         @JsonAlias("languages") List<String> idiomas,
                         @JsonAlias("download_count") double numeroDeDescargas ) {

    public String toString() {
        return
                "Titulo='" + titulo + "\n" +
                "Autor=" + autor + "\n" +
                "Idiomas=" + idiomas + "\n" +
                "Número de Descargas=" + numeroDeDescargas;
    }
}
