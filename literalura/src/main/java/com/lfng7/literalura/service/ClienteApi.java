package com.lfng7.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteApi {
    public String obtenerDatos(String url) {
        System.out.println("* Conectando con el api, espere...");
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> respuesta = null;
        try {
            respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
            System.out.println("* Estado de coneccion con el api : " + respuesta.statusCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return respuesta.body();
    }
}
