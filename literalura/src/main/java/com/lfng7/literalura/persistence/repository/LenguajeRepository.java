package com.lfng7.literalura.persistence.repository;

import com.lfng7.literalura.persistence.entity.LenguajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LenguajeRepository extends JpaRepository<LenguajeEntity, Long> {
    Optional<LenguajeEntity> findByLanguage(String language);
}
