package com.lfng7.literalura.persistence.repository;

import com.lfng7.literalura.persistence.entity.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
    @Override
    List<AutorEntity> findAll();

    Optional<AutorEntity> findByName(String name);

    @Query("SELECT a FROM AutorEntity a WHERE  CAST(a.birthYear AS integer) > :desde AND CAST(a.deathYear AS integer) <= :hasta")
    List<AutorEntity> autoresVivosRangoAnos(int desde, int hasta);
}
