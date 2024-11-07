package com.lfng7.literalura.persistence.repository;

import com.lfng7.literalura.persistence.entity.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {
    Optional<LibroEntity> findByIdApi(long idApi);
    Optional<LibroEntity> findByTitle(String title);
}
