package com.example.certificate.repository;


import com.example.certificate.entity.Exam;
import com.example.certificate.entity.ExamLog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExamRepository {
    private final EntityManager em;

    public  void saveExamLog(ExamLog examLog){
        em.persist(examLog);
    }
    public List<Exam> getExam (String userId,int examNum){
        return em.createQuery("SELECT m FROM Exam ",Exam.class).getResultList();
    }

    public  ExamLog getRecentExamLog(String userId){
        //가장 최근에 테스트를 친 기록
        return em.createQuery("SELECT m FROM ExamLog m where m.userId=:userId ORDER BY m.examDate DESC",ExamLog.class).
                setParameter("userId",userId)
                .setMaxResults(1) // 가장 첫 번째 값 하나만 반환
                .getSingleResult();
    }
    public  Exam getRecentExam(ExamLog examLog,int examNum){
        return em.createQuery("SELECT e FROM Exam e WHERE e.examLog =:examLog AND e.examNum =:examNum",Exam.class).
                setParameter("examLog",examLog).
                setParameter("examNum",examNum).
                getSingleResult();
//        String jpql = "SELECT e FROM Exam e WHERE e.examLog = :examLog AND e.examNum = :examNum";
//        TypedQuery<Exam> query = em.createQuery(jpql, Exam.class);
//        query.setParameter("examLog", examLog);
//        query.setParameter("examNum", examNum);

        //return query.getSingleResult();
    }
}
