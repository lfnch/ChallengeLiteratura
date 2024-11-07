package com.lfng7.literalura.persistence.repository;

import com.lfng7.literalura.persistence.entity.AutorEntity;
import com.lfng7.literalura.persistence.entity.TraductorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraductorRepository extends JpaRepository<TraductorEntity, Long> {
    Optional<TraductorEntity> findByName(String name);
}
