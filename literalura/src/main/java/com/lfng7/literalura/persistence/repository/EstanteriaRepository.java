package com.lfng7.literalura.persistence.repository;

import com.lfng7.literalura.persistence.entity.EstanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstanteriaRepository extends JpaRepository<EstanteEntity, Long> {
    Optional<EstanteEntity> findByBookshelf(String bookshelf);
}
