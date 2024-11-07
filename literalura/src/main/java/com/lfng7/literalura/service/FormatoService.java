package com.lfng7.literalura.service;

import com.lfng7.literalura.persistence.entity.FormatoEntity;
import com.lfng7.literalura.persistence.repository.FormatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FormatoService {

    @Autowired
    private FormatoRepository repository;

    @Transactional
    public void save(FormatoEntity formato) {
        Optional<FormatoEntity> existingFormato = repository.findByFormat(formato.getFormat());
        if (existingFormato.isEmpty()) {
            repository.save(formato);
            System.out.println("Formato guardado exitosamente.");
        } else {
            System.out.println("El formato ya existe y no se guardará nuevamente.");
        }
    }

    @Transactional
    public FormatoEntity existeFormato(FormatoEntity formato) {
        Optional<FormatoEntity> existingFormato = repository.findByFormat(formato.getFormat());
        if (existingFormato.isPresent()) {
            return existingFormato.get();
        } else {
            return null;
        }
    }
}
