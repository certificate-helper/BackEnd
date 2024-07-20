package com.example.certificate.repository;

import com.example.certificate.entity.MyVoca;
import com.example.certificate.entity.VocabularyList;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyVocaRepository {
    private final EntityManager em;
    public List<MyVoca> getMyVoca(String userId, String  voca){
        return em.createQuery("SELECT m FROM MyVoca m WHERE  m.userId =:userId and m.voca =:voca"  , MyVoca.class)
                .setParameter("userId", userId)
                .setParameter("voca", voca)
                .getResultList();
    }
    public void saveMyVoca(MyVoca myVoca){
        em.persist(myVoca);
    }
}
