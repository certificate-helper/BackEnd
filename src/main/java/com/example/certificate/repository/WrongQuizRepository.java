package com.example.certificate.repository;


import com.example.certificate.entity.QuizLog;
import com.example.certificate.entity.WrongQuiz;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WrongQuizRepository {

    private final EntityManager em;

    public List<WrongQuiz> getUserWrongQuiz(QuizLog quizLog) {
        return em.createQuery("SELECT w FROM WrongQuiz w where w.quizLog =:quizLog",WrongQuiz.class)
                .setParameter("quizLog",quizLog)
                .getResultList();
    }
}
