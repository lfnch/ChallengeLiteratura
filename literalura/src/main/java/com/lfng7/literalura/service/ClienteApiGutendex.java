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
                                        .map(autor -> autor.nombre().replace(",", ""))
                                        .collect(Collectors.joining("; "));

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
                                        .map(autor -> autor.nombre().replace(",", ""))
                                        .collect(Collectors.joining("; "));

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
                                .map(autor -> autor.nombre().replace(",", ""))
                                .collect(Collectors.joining("; "));

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
        int opcion = 0;
        int pagina = 1;
        long registrosEncontrados = 0;
        double totalPaginas = 1;
        String lenguajeBuscar = "";
        String opciones = """
            Seleccione una opcion :
            1. Buscar lenguaje
            2. Anterior pagina <-
            3. Siguiente pagina ->
            4. Salir
            """;

        try {
            while (opcion != 4) {
                scanner.nextLine();

                if(opcion == 1) {
                    System.out.println("""
                        Ingrese un lenguaje : 
                        EN -> Ingles
                        ES -> Español
                        FR -> Frances
                        DE -> Aleman
                        IT -> Italiano
                    """);
                    lenguajeBuscar = scanner.nextLine().trim();
                }

                //Pagina Anterior
                if(opcion == 2 && pagina > 1) {
                    pagina = pagina - 1;
                }

                //Pagina Siguiente
                if(opcion == 3 && pagina < totalPaginas) {
                    pagina = pagina + 1;
                }

                if(!lenguajeBuscar.isEmpty()) {
                    if(opcion == 1) {
                        lenguajeBuscar = lenguajeBuscar.toLowerCase().replace(" ", "%20");
                    }

                    String datosApi = clienteApi.obtenerDatos(URL_API + "/?page=" + pagina +
                            "&languages=" + lenguajeBuscar);

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
                                .map(autor -> autor.nombre().replace(",", ""))
                                .collect(Collectors.joining("; "));
                        String languages = libro.lenguajes().stream()
                                .collect(Collectors.joining(", "));

                        System.out.println("LIBRO : " + titulo + " | AUTORES : " + autores + " | LANGUAGES : " + languages);
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

    public void buscarPorRangoAnos(Scanner scanner) {
        /*
        author_year_start y author_year_end
        Utilícelos para buscar libros con al menos un autor vivo en un rango de años determinado.
        Deben tener valores enteros positivos o negativos. Por ejemplo,
        /books?author_year_end=-499ofrece libros con autores vivos antes del 500 a. C. y
        /books?author_year_start=1800&author_year_end=1899 ofrece libros con autores
        vivos en el siglo XIX.
         */
        String anoInicio = "";
        String anoFinal = "";
        int opcion = 0;
        int pagina = 1;
        long registrosEncontrados = 0;
        double totalPaginas = 1;
        String fechaBuscar = "";
        String opciones = """
            Seleccione una opcion :
            1. Buscar por rango de años
            2. Anterior pagina <-
            3. Siguiente pagina ->
            4. Salir
            """;

        try {
            while (opcion != 4) {
                scanner.nextLine();

                if(opcion == 1) {
                    System.out.println("""
                        Ingrese rango de años de, hasta : 
                        Ejemplo : 1993-2024
                        """);
                    fechaBuscar = scanner.nextLine().trim();
                }

                //Pagina Anterior
                if(opcion == 2 && pagina > 1) {
                    pagina = pagina - 1;
                }

                //Pagina Siguiente
                if(opcion == 3 && pagina < totalPaginas) {
                    pagina = pagina + 1;
                }

                if(!fechaBuscar.isEmpty()) {
                    if(opcion == 1) {
                        String patron = "\\d{4}-\\d{4}";
                        boolean esValido = fechaBuscar.matches(patron);

                        if(!esValido) {
                            fechaBuscar = "";
                            System.out.println("Formato de rango no valido");
                            continue;
                        }

                        String partes[] = fechaBuscar.split("-");
                        anoInicio = partes[0];
                        anoFinal = partes[1];

                        if(Integer.parseInt(anoInicio) > Integer.parseInt(anoFinal)) {
                            fechaBuscar = "";
                            System.out.println("La fecha final no puede ser menor a la fecha de inicio.");
                            continue;
                        }

                        //lenguajeBuscar = fechaBuscar.toLowerCase().replace(" ", "%20");
                    }

                    String datosApi = clienteApi.obtenerDatos(URL_API + "/?page=" + pagina +
                            "&author_year_start=" + anoInicio + "&author_year_end=1899=" + anoFinal);

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
                                .map(autor -> autor.nombre().replace(",", ""))
                                .collect(Collectors.joining("; "));

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
}
