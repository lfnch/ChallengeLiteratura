package com.lfng7.literalura.persistence.repository;

import com.lfng7.literalura.persistence.entity.SubjectoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectoRepository extends JpaRepository<SubjectoEntity, Long> {
    Optional<SubjectoEntity> findBySubject(String subject);
}
