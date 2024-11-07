package com.lfng7.literalura.persistence.repository;

import com.lfng7.literalura.persistence.entity.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {

    @Override
    List<LibroEntity> findAll();

    Optional<LibroEntity> findByIdApi(long idApi);

    Optional<LibroEntity> findByTitle(String title);

    List<LibroEntity> findTop10ByOrderByDownloadCountDesc();

    List<LibroEntity> findByTitleContainingIgnoreCase(String titlePart);

    @Query("SELECT lb FROM LibroEntity lb JOIN lb.lenguajes lg WHERE lg.language = :idioma")
    List<LibroEntity> buscarPorIdioma(String idioma);
}
