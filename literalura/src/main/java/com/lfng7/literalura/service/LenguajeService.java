package com.lfng7.literalura.service;

import com.lfng7.literalura.persistence.entity.LenguajeEntity;
import com.lfng7.literalura.persistence.repository.LenguajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LenguajeService {

    @Autowired
    private LenguajeRepository repository;

    @Transactional
    public void save(LenguajeEntity lenguaje) {
        Optional<LenguajeEntity> existingLenguaje = repository.findByLanguage(lenguaje.getLanguage());
        if (existingLenguaje.isEmpty()) {
            repository.save(lenguaje);
            System.out.println("Lenguaje guardado exitosamente.");
        } else {
            System.out.println("El lenguaje ya existe y no se guardará nuevamente.");
        }
    }

    @Transactional
    public LenguajeEntity existeLenguaje(LenguajeEntity estante) {
        Optional<LenguajeEntity> existingLenguaje = repository.findByLanguage(estante.getLanguage());
        if (existingLenguaje.isPresent()) {
            return existingLenguaje.get();
        } else {
            return null;
        }
    }
}
