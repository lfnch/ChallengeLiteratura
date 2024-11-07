package com.lfng7.literalura.service;

import com.lfng7.literalura.persistence.entity.TraductorEntity;
import com.lfng7.literalura.persistence.repository.TraductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TraductorService {

    @Autowired
    private TraductorRepository repository;

    @Transactional
    public void save(TraductorEntity traductor) {
        Optional<TraductorEntity> existingTraductor = repository.findByName(traductor.getName());
        if (existingTraductor.isEmpty()) {
            repository.save(traductor);
            System.out.println("Traductor guardado exitosamente.");
        } else {
            System.out.println("El traductor ya existe y no se guardará nuevamente.");
        }
    }

    @Transactional
    public TraductorEntity existeTraductor(TraductorEntity traductor) {
        Optional<TraductorEntity> existingTraductor= repository.findByName(traductor.getName());
        if (existingTraductor.isPresent()) {
            return existingTraductor.get();
        } else {
            return null;
        }
    }
}
