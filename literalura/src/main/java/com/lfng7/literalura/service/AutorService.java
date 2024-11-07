package com.lfng7.literalura.service;

import com.lfng7.literalura.persistence.entity.AutorEntity;
import com.lfng7.literalura.persistence.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    @Transactional
    public void save(AutorEntity autor) {
        Optional<AutorEntity> existingAutor = repository.findByName(autor.getName());
        if (existingAutor.isEmpty()) {
            repository.save(autor);
            System.out.println("Autor guardado exitosamente.");
        } else {
            System.out.println("El autor ya existe y no se guardará nuevamente.");
        }
    }

    @Transactional
    public AutorEntity existeAutor(AutorEntity autor) {
        Optional<AutorEntity> existingAutor = repository.findByName(autor.getName());
        if (existingAutor.isPresent()) {
            return existingAutor.get();
        } else {
            return null;
        }
    }
}
