package com.lfng7.literalura;

import com.lfng7.literalura.model.LibroDto;
import com.lfng7.literalura.persistence.entity.*;
import com.lfng7.literalura.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    private final LibroService libroService;
    private final AutorService autorService;
    private final TraductorService traductorServicio;
    private final EstanteService estanteService;
    private final LenguajeService lenguajeService;
    private final SubjectoService subjectoService;
    private final FormatoService formatoService;

    private Scanner scanner = new Scanner(System.in);
    private ClienteApiGutendex clienteApiGutendex = new ClienteApiGutendex();
    private ClienteApiGutendexDb clienteApiGutendexDb = new ClienteApiGutendexDb();

    @Autowired
    public LiteraluraApplication(LibroService libroService, AutorService autorService, TraductorService traductorServicio, EstanteService estanteService, LenguajeService lenguajeService, SubjectoService subjectoService, FormatoService formatoService) {
        this.libroService = libroService;
        this.autorService = autorService;
        this.traductorServicio = traductorServicio;
        this.estanteService = estanteService;
        this.lenguajeService = lenguajeService;
        this.subjectoService = subjectoService;
        this.formatoService = formatoService;
    }

    public static void main(String[] args) {SpringApplication.run(LiteraluraApplication.class, args);}

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
            4 -> Ver todo autores.
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
                    case 1 -> {

                        System.out.println("Buscando libros.");
                        for(int pagina = 1; pagina <= 10; pagina++) {
                            System.out.println("Pagina : " + pagina);
                            List<LibroDto> libros = clienteApiGutendexDb.obtenerCatalogoLibros(pagina);

                            System.out.println("Guardando libros encontrados.");

                            libros.stream().forEach(libroDto -> {
                                LibroEntity libro = new LibroEntity(libroDto);
                                libro.setAutores(new ArrayList<>());
                                libro.setTraductores(new ArrayList<>());
                                libro.setEstanterias(new ArrayList<>());
                                libro.setLenguajes(new ArrayList<>());
                                libro.setSubjectos(new ArrayList<>());
                                libro.setFormatos(new ArrayList<>());

                                //Autores
                                libroDto.autores().stream().forEach(personaDto -> {
                                    AutorEntity autor = new AutorEntity(personaDto);

                                    AutorEntity ExisteAutor = autorService.existeAutor(autor);
                                    if(ExisteAutor != null) {
                                        libro.getAutores().add(ExisteAutor);
                                    } else {
                                        autorService.save(autor);
                                        libro.getAutores().add(autor);
                                    }
                                });

                                //Traductores
                                libroDto.traductores().stream().forEach(personaDto -> {
                                    TraductorEntity traductor = new TraductorEntity(personaDto);

                                    TraductorEntity ExisteTraductor = traductorServicio.existeTraductor(traductor);
                                    if(ExisteTraductor != null) {
                                        libro.getTraductores().add(ExisteTraductor);
                                    } else {
                                        traductorServicio.save(traductor);
                                        libro.getTraductores().add(traductor);
                                    }
                                });

                                //Estanterias
                                libroDto.estanterias().stream().forEach(estanteDto -> {
                                    EstanteEntity estante = new EstanteEntity(estanteDto);

                                    EstanteEntity existeEstante = estanteService.existeEstante(estante);
                                    if(existeEstante != null) {
                                        libro.getEstanterias().add(existeEstante);
                                    } else {
                                        estanteService.save(estante);
                                        libro.getEstanterias().add(estante);
                                    }
                                });

                                //Lenguajes
                                libroDto.lenguajes().stream().forEach(lenguajeDto -> {
                                    LenguajeEntity lenguaje = new LenguajeEntity(lenguajeDto);

                                    LenguajeEntity exiteLenguaje = lenguajeService.existeLenguaje(lenguaje);
                                    if(exiteLenguaje != null) {
                                        libro.getLenguajes().add(exiteLenguaje);
                                    } else {
                                        lenguajeService.save(lenguaje);
                                        libro.getLenguajes().add(lenguaje);
                                    }
                                });

                                libroDto.formatos().forEach((formatoDto, url) -> {
                                    FormatoEntity formato = new FormatoEntity(formatoDto);

                                    FormatoEntity exiteFormato = formatoService.existeFormato(formato);
                                    if(exiteFormato != null) {
                                        libro.getFormatos().add(exiteFormato);
                                    } else {
                                        formatoService.save(formato);
                                        libro.getFormatos().add(formato);
                                    }
                                });

                                //Subjectos
                                libroDto.lenguajes().stream().forEach(subjectoDto -> {
                                    SubjectoEntity subjecto = new SubjectoEntity(subjectoDto);

                                    SubjectoEntity exiteSubjecto = subjectoService.existeSubjecto(subjecto);
                                    if(exiteSubjecto != null) {
                                        libro.getSubjectos().add(exiteSubjecto);
                                    } else {
                                        subjectoService.save(subjecto);
                                        libro.getSubjectos().add(subjecto);
                                    }
                                });

                                libroService.save(libro);
                            });
                        }
                        System.out.println("Proceso terminado.\n");
                    }

                    case 2 -> {
                        List<LibroEntity> libros = libroService.findAll();
                        if(libros.stream().count() > 0) {
                            libros.stream().forEach(libro -> System.out.println("LIBRO : " + libro.getTitle()));
                        } else {
                            System.out.println("No se encontraron registros");
                        }
                    }

                    //Buscar libro por nombre
                    case 3 -> {
                        System.out.println("Ingrese un nombre : ");
                        scanner.nextLine();
                        String nombreBuscar = scanner.nextLine();
                        if(!nombreBuscar.equals("")) {
                            List<LibroEntity> libros = libroService.findByTitle(nombreBuscar);
                            if(libros.stream().count() > 0) {
                                libros.stream().forEach(libro -> System.out.println("LIBRO : " + libro.getTitle()));
                            } else {
                                System.out.println("No se encontraron registros");
                            }
                        }
                    }

                    //Buscar autores
                    case 4 -> {
                        List<AutorEntity> autores = autorService.findAll();
                        if(autores.stream().count() > 0) {
                            autores.stream().forEach(autor -> System.out.println("Autor : " + autor.getName()));
                        } else {
                            System.out.println("No se encontraron registros");
                        }
                    }

                    //Buscar libro por idioma
                    case 5 -> {
                        System.out.println("""
                        Ingrese un lenguaje : 
                        EN -> Ingles
                        ES -> Español
                        FR -> Frances
                        DE -> Aleman
                        IT -> Italiano
                        """);
                        scanner.nextLine();
                        String idioma = scanner.nextLine().trim();
                        if(!idioma.equals("")) {
                            List<LibroEntity> libros = libroService.buscarPorIdioma(idioma);
                            if(libros.stream().count() > 0) {
                                libros.stream().forEach(libro -> System.out.println("LIBRO : " + libro.getTitle()));
                            } else {
                                System.out.println("No se encontraron registros");
                            }
                        }
                    }

                    default -> {if(opcion > 7) {System.out.println(mensajeError);}}
                }
            } catch (RuntimeException e) {
                System.out.println(mensajeError);
                System.out.println(e.getMessage());
                scanner.next();
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
                    case 1 -> {
                        List<LibroEntity> libros = libroService.top10();
                        libros.stream().forEach(libro -> System.out.println("LIBRO : " + libro.getTitle() +
                                "DESCARGAS : " + libro.getDownloadCount()));
                    }
                    default -> {if(opcion > 2) {System.out.println(mensajeError);}}
                }
            } catch (RuntimeException e) {
                scanner.next();
                System.out.println(mensajeError);
            }
        }
    }
}
