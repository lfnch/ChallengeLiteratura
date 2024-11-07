package com.lfng7.literalura.service;

import com.lfng7.literalura.persistence.entity.EstanteEntity;
import com.lfng7.literalura.persistence.repository.EstanteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EstanteService {

    @Autowired
    private EstanteriaRepository repository;

    @Transactional
    public void save(EstanteEntity estante) {
        Optional<EstanteEntity> existingEstante = repository.findByBookshelf(estante.getBookshelf());
        if (existingEstante.isEmpty()) {
            repository.save(estante);
            System.out.println("Estante guardado exitosamente.");
        } else {
            System.out.println("El estante ya existe y no se guardará nuevamente.");
        }
    }

    @Transactional
    public EstanteEntity existeEstante(EstanteEntity estante) {
        Optional<EstanteEntity> existingEstante = repository.findByBookshelf(estante.getBookshelf());
        if (existingEstante.isPresent()) {
            return existingEstante.get();
        } else {
            return null;
        }
    }
}
