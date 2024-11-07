package com.lfng7.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lfng7.literalura.model.LibroDto;
import com.lfng7.literalura.model.ListaLibroDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteApiGutendexDb {

    private final String URL_API = "https://gutendex.com/books/";
    private ClienteApi clienteApi = new ClienteApi();
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<LibroDto> obtenerCatalogoLibros(int pagina){
        String datosApi = clienteApi.obtenerDatos(URL_API + "?page=" + pagina);
        List<LibroDto> libros = null;

        try {
            //Convertir los datos
            var listaLibros = objectMapper.readValue(datosApi, ListaLibroDto.class);
            libros = listaLibros.resultados();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return libros;
    }
}
