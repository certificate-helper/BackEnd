package com.example.certificate.repository;


import com.example.certificate.entity.MyVoca;
import com.example.certificate.entity.WrongAnswer;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WrongAnswerRepository {
    private final EntityManager em;


    public List<WrongAnswer> getUserWrongAnswer(String userId) {
        return em.createQuery(
                        "SELECT wa FROM WrongAnswer wa " +
                                "WHERE wa.testType = 'quiz' " +
                                "AND wa.userId = :userId " +
                                "AND wa.examLog.id = (" +
                                "    SELECT MAX(innerWa.examLog.id) FROM WrongAnswer innerWa " +
                                "    WHERE innerWa.userId = :userId AND innerWa.testType = 'quiz'" +
                                ")",
                        WrongAnswer.class)
                .setParameter("userId", userId)
                .getResultList();
    }



}
