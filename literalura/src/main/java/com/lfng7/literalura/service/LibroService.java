package com.lfng7.literalura.service;

import com.lfng7.literalura.persistence.entity.LibroEntity;
import com.lfng7.literalura.persistence.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository repository;

    @Transactional
    public void save(LibroEntity libro) {
        Optional<LibroEntity> existingLibro = repository.findByIdApi(libro.getIdApi());
        Optional<LibroEntity> existingLibroTitle = repository.findByTitle(libro.getTitle());

        if (existingLibro.isEmpty() && existingLibroTitle.isEmpty()) {
            repository.save(libro);
            System.out.println("Libro guardado exitosamente.");
        } else {
            System.out.println("El libro ya existe y no se guardará nuevamente.");
        }
    }

}
