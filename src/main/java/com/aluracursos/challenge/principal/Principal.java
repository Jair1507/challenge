package com.aluracursos.challenge.principal;

import com.aluracursos.challenge.model.Autor;
import com.aluracursos.challenge.model.DatosLibro;
import com.aluracursos.challenge.model.Datos;
import com.aluracursos.challenge.model.Libro;
import com.aluracursos.challenge.repository.LibroRepository;
import com.aluracursos.challenge.service.ConsumoAPI;
import com.aluracursos.challenge.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;
    private ConsumoAPI consumoAPI = new ConsumoAPI();


    public Principal(LibroRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 -Buscar libro por titulo
                    2 -Listar libros registrados
                    3 -Listar autores registrados
                    4- Listar autores vivos en determinado año
                    5- Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    obtenerLibroPorTitulo();
                    break;
                case 2:
                    obtenerListaDeLibros();
                    break;
                case 3:
                    obtenerListaDeAutores();
                    break;
                case 4:
                    obtenerAutoresPorFecha();
                    break;
                case 5:
                    obtenerLibrosPorIdioma();
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    public void obtenerLibroPorTitulo() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
        var datos = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibro> libroBuscado = datos.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            DatosLibro datosLibro = libroBuscado.get();
            // Verificar si el libro ya existe en la base de datos
            boolean libroExiste = repositorio.existsByTitulo(datosLibro.titulo());

            if (libroExiste) {
                System.out.println("Libro ya registrado en la base de datos:");
                System.out.println(datosLibro);
            } else {
                Libro libro = new Libro(datosLibro);
                repositorio.save(libro);
                System.out.println("Libro encontrado y guardado en la base de datos:");
                System.out.println(libro);
            }
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    public void obtenerListaDeLibros() {
        List<Libro> libros = repositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la base de datos.");
        } else {
            System.out.println("Lista de libros en la base de datos:");
            libros.forEach(libro -> System.out.println(libro.toString()));
        }
    }

    public void obtenerListaDeAutores() {
        List<String> autoresNombres = repositorio.findAllAutoresNombres();
        if (autoresNombres.isEmpty()) {
            System.out.println("No hay autores en la base de datos.");
        } else {
            System.out.println("Lista de nombres de autores en la base de datos:");
            autoresNombres.forEach(System.out::println);
        }
    }

    public void obtenerAutoresPorFecha(){
        System.out.println("Ingresa el año hasta el cual deseas buscar autores fallecidos:");
        int ano = teclado.nextInt();


        // Obtener los autores fallecidos antes del año ingresado
        List<Autor> autores = repositorio.findAutoresFallecidosAntesDe(ano);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores fallecidos antes del año " + ano + ".");
        } else {
            System.out.println("Autores fallecidos antes del año " + ano + ":");
            autores.forEach(autor -> System.out.println(autor.getNombre() + " - Fecha de Fallecimiento: " + autor.getFechaDeFallecimiento()));
        }
    }

    public void obtenerLibrosPorIdioma(){
        System.out.println("Ingresa el idioma de los libros que deseas buscar (separados por comas si hay más de uno):");
        String entrada = teclado.nextLine();
        List<String> idiomas = List.of(entrada.split("\\s*,\\s*"));

        // Obtener los libros por idioma
        List<Libro> libros = repositorio.findByIdiomaIn(idiomas);

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en los idiomas: " + idiomas);
        } else {
            System.out.println("Libros en los idiomas " + idiomas + ":");
            libros.forEach(libro -> System.out.println(libro.getTitulo() + " - Descargas: " + libro.getNumeroDeDescargas()));
        }
    }
}
