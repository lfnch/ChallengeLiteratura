package com.lfng7.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lfng7.literalura.model.ListaLibroRecord;

import java.util.Scanner;
import java.util.stream.Collectors;

/*
 * ClienteApiGutendex
 * Esta clase se encarga de las iteraciones con la api gutendex,
 * proporciona metoso basicos para conetarse a la api y mostras consultas
 */
public class ClienteApiGutendex {
    private final String URL_API = "https://gutendex.com/books";
    private ClienteApi clienteApi = new ClienteApi();
    private  ObjectMapper objectMapper = new ObjectMapper();

    /*
    * obtenerDatos
    * Se conecta al api, y obtiene el listado de libros e imprime
    * por pagina de 32 libros
    *
    * @param: Scanner
    * @void
    * */
    public void obtenerDatos(Scanner scanner) {
        int opcion = 1;
        int pagina = 1;
        String criterioBusqueda = "/?page=";
        String menu = """
                Seleccione una opcion :
                1. Anterior pagina <-
                2. Siguiente pagina ->
                3. Salir
                """;

        while (opcion != 3) {
            if(opcion == 1 || opcion == 2) {
                //Pagina Anterior
                if(opcion == 1 && pagina > 1) { pagina = pagina - 1; }

                //Pagina Siguiente
                if(opcion == 2) { pagina = pagina + 1; }

                //Conectar con api
                String datosApi = clienteApi.obtenerDatos(URL_API + criterioBusqueda + pagina);

                try {
                    //Convertir los datos
                    var listaLibros = objectMapper.readValue(datosApi, ListaLibroRecord.class);
                    long totalPaginas = listaLibros.cuenta() / 32;

                    System.out.println("Pagina : " + pagina + " de : " + totalPaginas);

                    /*Recorrer los libros*/
                    listaLibros.resultados().stream()
                            .forEach(libro -> {
                                String titulo =  libro.titulo();
                                String autores = libro.autores().stream()
                                        .map(autor -> autor.nombre())
                                        .collect(Collectors.joining(", "));

                                System.out.println("LIBRO : " + titulo + " | AUTORES : " + autores);
                            });

                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }

            //Imprime menu
            System.out.println(menu);

            try {
                opcion = scanner.nextInt();
            } catch (RuntimeException e){
                scanner.next();
                opcion = 4;
            }
        }
    }

    public void buscarPorNombreLibro(Scanner scanner) {
        int opcion = 0;
        int pagina = 1;
        long registrosEncontrados = 0;
        double totalPaginas = 1;
        String nombreBuscar = "";
        String opciones = """
            Seleccione una opcion :
            1. Buscar libro
            2. Anterior pagina <-
            3. Siguiente pagina ->
            4. Salir
            """;

        try {
            while (opcion != 4) {
                scanner.nextLine();

                if(opcion == 1) {
                    System.out.println("Ingrese un nombre : ");
                    nombreBuscar = scanner.nextLine().trim();
                }

                //Pagina Anterior
                if(opcion == 2 && pagina > 1) {
                    pagina = pagina - 1;
                }

                //Pagina Siguiente
                if(opcion == 3 && pagina < totalPaginas) {
                    pagina = pagina + 1;
                }

                if(!nombreBuscar.isEmpty()) {
                    if(opcion == 1) {
                        nombreBuscar = nombreBuscar.toLowerCase().replace(" ", "%20");
                    }

                    String datosApi = clienteApi.obtenerDatos(URL_API + "/?page=" + pagina +
                            "&search=" + nombreBuscar);

                    var listaLibros = objectMapper.readValue(datosApi, ListaLibroRecord.class);
                    registrosEncontrados =  listaLibros.cuenta();

                    if(registrosEncontrados > 0) {
                        totalPaginas = Math.ceil((double) registrosEncontrados / 32.0);
                    }

                    System.out.println("Registros encontrados : " + registrosEncontrados);
                    System.out.println("Pagina : " + pagina + " de : " + totalPaginas);

                    listaLibros.resultados().stream().forEach(libro -> {
                                String titulo =  libro.titulo();
                                String autores = libro.autores().stream()
                                        .map(autor -> autor.nombre())
                                        .collect(Collectors.joining(", "));

                                System.out.println("LIBRO : " + titulo + " | AUTORES : " + autores);
                            });

                    opcion = 0;
                }

                System.out.println(opciones);
                opcion = scanner.nextInt();
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            scanner.next();
            this.buscarPorNombreLibro(scanner);
        }
    }

    public void buscarPorNombreAutor(Scanner scanner) {
        int opcion = 0;
        int pagina = 1;
        long registrosEncontrados = 0;
        double totalPaginas = 1;
        String nombreBuscar = "";
        String opciones = """
            Seleccione una opcion :
            1. Buscar autor
            2. Anterior pagina <-
            3. Siguiente pagina ->
            4. Salir
            """;

        try {
            while (opcion != 4) {
                scanner.nextLine();

                if(opcion == 1) {
                    System.out.println("Ingrese un nombre : ");
                    nombreBuscar = scanner.nextLine().trim();
                }

                //Pagina Anterior
                if(opcion == 2 && pagina > 1) {
                    pagina = pagina - 1;
                }

                //Pagina Siguiente
                if(opcion == 3 && pagina < totalPaginas) {
                    pagina = pagina + 1;
                }

                if(!nombreBuscar.isEmpty()) {
                    if(opcion == 1) {
                        nombreBuscar = nombreBuscar.toLowerCase().replace(" ", "%20");
                    }

                    String datosApi = clienteApi.obtenerDatos(URL_API + "/?page=" + pagina +
                            "&search=" + nombreBuscar);

                    var listaLibros = objectMapper.readValue(datosApi, ListaLibroRecord.class);

                    String finalNombreBuscar = nombreBuscar;
                    var listaLibrosAutor = listaLibros.resultados().stream()
                            .filter(libro -> libro.autores().stream()
                                    .anyMatch(autor -> autor.nombre().toLowerCase().replace(" ", "%20").contains(finalNombreBuscar)))
                            .collect(Collectors.toList());

                    registrosEncontrados =  listaLibrosAutor.stream().count();

                    if(registrosEncontrados > 0) {
                        totalPaginas = Math.ceil((double) registrosEncontrados / 32.0);
                    }

                    System.out.println("Registros encontrados : " + registrosEncontrados);
                    System.out.println("Pagina : " + pagina + " de : " + totalPaginas);

                    listaLibrosAutor.stream().forEach(libro -> {
                        String titulo =  libro.titulo();
                        String autores = libro.autores().stream()
                                .map(autor -> autor.nombre())
                                .collect(Collectors.joining(", "));

                        System.out.println("LIBRO : " + titulo + " | AUTORES : " + autores);
                    });

                    opcion = 0;
                }

                System.out.println(opciones);
                opcion = scanner.nextInt();
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            scanner.next();
            this.buscarPorNombreAutor(scanner);
        }
    }

    public void buscarPorIdioma(Scanner scanner) {
        String CriterioBusqueda = "/books?languages=en";
    }

    public void buscarPorRangoAnos(Scanner scanner) {

    }
}
