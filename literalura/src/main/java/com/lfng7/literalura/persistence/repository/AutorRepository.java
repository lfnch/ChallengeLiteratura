package com.lfng7.literalura.persistence.repository;

import com.lfng7.literalura.persistence.entity.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
    Optional<AutorEntity> findByName(String name);
}
