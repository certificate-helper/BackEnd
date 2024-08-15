package com.example.certificate.repository;


import com.example.certificate.entity.ExamLog;
import com.example.certificate.entity.MyVoca;
import com.example.certificate.entity.WrongAnswer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WrongAnswerRepository {
    private final EntityManager em;


    public List<WrongAnswer> getUserWrongAnswer(String  userId) {
        return em.createQuery(
                        "SELECT wa FROM WrongAnswer wa WHERE wa.userId =:userId " , WrongAnswer.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<Object []> getUserWrongAnswerCount(String  userId) {
        String jpql = "SELECT wa.examLog, COUNT(wa) " +
                "FROM WrongAnswer wa " +
                "WHERE wa.userId = :userId " +
                "GROUP BY wa.examLog";

        // 쿼리 생성 및 파라미터 설정
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        query.setParameter("userId", userId);

        // 쿼리 실행 및 결과 반환
        return query.getResultList();
    }




}
