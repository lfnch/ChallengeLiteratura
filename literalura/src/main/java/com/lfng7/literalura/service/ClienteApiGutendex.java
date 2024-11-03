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
                1. Anterior <-
                2. Siguiente ->
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
        String criterioBusqueda = "/books?search=dickens%20grea";
    }

    public void buscarPorNombreAutor(Scanner scanner) {

    }

    public void buscarPorIdioma(Scanner scanner) {
        String CriterioBusqueda = "/books?languages=en";
    }

    public void buscarPorRangoAnos(Scanner scanner) {

    }
}
