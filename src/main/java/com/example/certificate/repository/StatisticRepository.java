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

    public List<Object []> getProblemFrequency(String userId){
        return  em.createQuery(
                        "SELECT w.problem, COUNT(w) " +
                                "FROM WrongQuiz w " +
                                "WHERE w.userId = :userId " +
                                "GROUP BY w.problem", Object[].class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<Object[]> getCategoryCountsByUserId( String userId) {

        return em.createQuery("SELECT wa.category, COUNT(wa) " +
                "FROM WrongAnswer wa " +
                "WHERE wa.userId = :userId " +
                "GROUP BY wa.category", Object[].class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<Object[]> getCategoryTrend(){
        return em.createQuery("SELECT wa.category, COUNT(wa) " +
                        "FROM Test wa " +
                        "GROUP BY wa.category", Object[].class)
                .getResultList();
    }
}
