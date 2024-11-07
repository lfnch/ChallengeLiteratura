package com.lfng7.literalura.persistence.repository;

import com.lfng7.literalura.persistence.entity.EstanteEntity;
import com.lfng7.literalura.persistence.entity.FormatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormatoRepository extends JpaRepository<FormatoEntity, Long> {
    Optional<FormatoEntity> findByFormat(String format);
}