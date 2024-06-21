package com.aluracursos.challenge.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    @ManyToMany(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;
    private List<String> idioma;
    private double numeroDeDescargas;

    public Libro() {
    }

    public Libro(DatosLibro datos) {
        this.titulo = datos.titulo();
        this.autores = datos.autor().stream()
                .map(Autor::new)
                .collect(Collectors.toList());
        this.idioma = datos.idiomas();
        this.numeroDeDescargas = datos.numeroDeDescargas();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return
                "Titulo= "  + titulo + "\n";
    }
}