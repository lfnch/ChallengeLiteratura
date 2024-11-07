package com.lfng7.literalura.service;

import com.lfng7.literalura.persistence.entity.SubjectoEntity;
import com.lfng7.literalura.persistence.repository.SubjectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SubjectoService {

    @Autowired
    private SubjectoRepository repository;

    @Transactional
    public void save(SubjectoEntity subjecto) {
        Optional<SubjectoEntity> existingSubjecto = repository.findBySubject(subjecto.getSubject());
        if (existingSubjecto.isEmpty()) {
            repository.save(subjecto);
            System.out.println("Subjecto guardado exitosamente.");
        } else {
            System.out.println("El Subjecto ya existe y no se guardará nuevamente.");
        }
    }

    @Transactional
    public SubjectoEntity existeSubjecto(SubjectoEntity subjecto) {
        Optional<SubjectoEntity> existingSubjecto = repository.findBySubject(subjecto.getSubject());
        if (existingSubjecto.isPresent()) {
            return existingSubjecto.get();
        } else {
            return null;
        }
    }
}
