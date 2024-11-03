package com.lfng7.literalura.ui;

import com.lfng7.literalura.service.ClienteApiGutendex;
import org.springframework.boot.CommandLineRunner;
import java.util.Arrays;
import java.util.Scanner;

public class Menu implements CommandLineRunner {

    private Scanner scanner = new Scanner(System.in);
    private  ClienteApiGutendex clienteApiGutendex = new ClienteApiGutendex();

    @Override
    public void run(String... args) throws Exception {
        System.out.println("""
                ************************************
                Bienvenido a LiterAlura,
                Su Biblioteca.
                ************************************
                """);
        this.menuPrincipal();
        System.out.println("""
                ************************************
                Por : Luis Felipe Nieves.
                Grupo G7 Alura Latam
                ************************************
                """);

    }

    private void menuPrincipal() {
        int opcion = 0;
        String menu = """
                Seleccione una opcion
                1 -> Catalogo directo API.
                2 -> Catalogo Base datos.
                3 -> Estadisticas.
                4 -> Salir.
                """;
        String mensajeError = "Entrada no valida, intente de nuevo";

        while (opcion != 4) {
            System.out.println(menu);
            try {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1 -> this.menuApi(); //Catalogo directo API
                    case 2 -> this.menuBaseDatos();  //Catalogo Base datos
                    case 3 -> this.menuEstadisticas(); //Estadisticas
                    default -> {if(opcion > 4) { System.out.println(mensajeError); }}
                }
            } catch (RuntimeException e) {
                scanner.next();
                System.out.println(mensajeError);
            }
        }
    }

    private void menuApi() {
        int opcion = 0;
        String menu = """
                MENU : DIRECTO API
                1 -> Ver todo catalogo.
                2 -> Buscar libro.
                3 -> Buscar libro por autor.
                4 -> Buscar libro por idioma.
                5 -> Buscar libro por rango de años.
                6 -> Regresar.
                """;
        String mensajeError = "Entrada no valida, intente de nuevo";

        while (opcion != 6) {
            System.out.println(menu);
            try {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1 -> clienteApiGutendex.obtenerDatos(scanner);  //Ver todo catalogo
                    case 2 -> clienteApiGutendex.buscarPorNombreLibro(scanner);  //Buscar libro.
                    case 3 -> clienteApiGutendex.buscarPorNombreAutor(scanner); //Buscar libro por autor.
                    case 4 -> clienteApiGutendex.buscarPorIdioma(scanner); //Buscar libro por idioma.
                    case 5 -> clienteApiGutendex.buscarPorRangoAnos(scanner); //Buscar libro por rango de años.
                    default -> {if(opcion > 6) {System.out.println(mensajeError);}}
                }
            } catch (RuntimeException e) {
                scanner.next();
                System.out.println(mensajeError);
            }
        }
    }

    private void menuBaseDatos() {
        int opcion = 0;
        String menu = """
            MENU : BASE DE DATOS
            1 -> Actualizar catalogo de libros.
            2 -> Ver todo catalogo.
            3 -> Buscar libro.
            4 -> Buscar libro por autor.
            5 -> Buscar libro por idioma.
            6 -> Buscar libro por rango de años.
            7 -> Regresar.
            """;
        String mensajeError = "Entrada no valida, intente de nuevo";
        while (opcion != 7) {
            System.out.println(menu);
            try {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1 -> System.out.println("");
                    case 2 -> System.out.println("");
                    case 3 -> System.out.println("");
                    case 4 -> System.out.println("");
                    case 5 -> System.out.println("");
                    default -> {if(opcion > 7) {System.out.println(mensajeError);}}
                }
            } catch (RuntimeException e) {
                scanner.next();
                System.out.println(mensajeError);
            }
        }
    }

    private void menuEstadisticas() {
        int opcion = 0;
        String menu = """
            MENU : ESTADISTICAS
            1 -> Top 10 libros mas descargados.
            2 -> Regresar.
            """;
        String mensajeError = "Entrada no valida, intente de nuevo";
        while (opcion != 2) {
            System.out.println(menu);
            try {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1 -> System.out.println("");
                    default -> {if(opcion > 2) {System.out.println(mensajeError);}}
                }
            } catch (RuntimeException e) {
                scanner.next();
                System.out.println(mensajeError);
            }
        }
    }
}

