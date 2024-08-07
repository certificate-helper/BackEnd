package com.example.certificate.repository;

import com.example.certificate.entity.Exam;
import com.example.certificate.entity.Test;
import com.example.certificate.entity.VocabularyList;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VocaRepository {
    private  final EntityManager em;
    public void insertVoca(VocabularyList vocabularyList){
        em.persist(vocabularyList);
    }


    public List<VocabularyList> searchVoca(String voca){
        return em.createQuery("SELECT m FROM VocabularyList m WHERE  m.voca =:voca"  , VocabularyList.class)
                .setParameter("voca", voca)
                .getResultList();
    }
    public List<VocabularyList> getVoca(int num){ //단어를 5개씩 불러오기
        return em.createQuery("SELECT m FROM VocabularyList m " , VocabularyList.class)
                .setFirstResult(num)
                .setMaxResults(5)
                .getResultList();
    }
    public List<VocabularyList> getAllVoca(){   //모든 단어 불러오기
        return em.createQuery("SELECT m FROM VocabularyList m " , VocabularyList.class)
                .getResultList();
    }
}
