package com.example.certificate.repository;

import com.example.certificate.entity.VocabularyList;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VocaRepository {
    private  final EntityManager em;
    public void insertVoca(VocabularyList vocabularyList){
        em.persist(vocabularyList);
    }
}
