package com.aluracursos.challenge.repository;

import com.aluracursos.challenge.model.Autor;
import com.aluracursos.challenge.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByTitulo(String titulo);
    @Query("SELECT DISTINCT a.nombre FROM Autor a")
    List<String> findAllAutoresNombres();
    @Query("SELECT DISTINCT a FROM Autor a WHERE a.fechaDeFallecimiento < :ano")
    List<Autor> findAutoresFallecidosAntesDe(int ano);
    List<Libro> findByIdiomaIn(List<String> idiomas);

}
