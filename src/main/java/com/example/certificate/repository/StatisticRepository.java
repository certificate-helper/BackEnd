package com.example.certificate.repository;


import com.example.certificate.entity.QuizLog;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticRepository {
    private final EntityManager em;
    public List<QuizLog> getUserQuizLog(String userId){
        return em.createQuery("SELECT m FROM QuizLog m where m.userId =:userId",QuizLog.class).
                setParameter("userId",userId).
                getResultList();
    }
}
