package com.example.certificate.repository;

import com.example.certificate.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

    public List<MyVoca> setQuiz (String userId){
        System.out.println("setQuiz: "+ userId);
        return em.createQuery("SELECT m FROM MyVoca m WHERE  m.userId =:userId ORDER BY RAND()"  , MyVoca.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<MyVoca> doMyVocaQuiz(String userId,int examNum){
        return em.createQuery("SELECT m FROM MyVoca m WHERE  m.userId =:userId and m.examNum =:examNum"  , MyVoca.class)
                .setParameter("userId", userId)
                .setParameter("examNum",examNum)
                .getResultList();
    }
    public  List<Exam> wrongAnswer(String userId){
        String jpql = "SELECT e FROM Exam e WHERE e.userTest.userId = :userId and e.state = -1";
        TypedQuery<Exam> query = em.createQuery(jpql, Exam.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    public List<Exam> getAllExam(String userId){
        String jpql = "SELECT e FROM Exam e WHERE e.userTest.userId = :userId";
        TypedQuery<Exam> query = em.createQuery(jpql, Exam.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public  List<Exam> doQuiz(String userId,int examNum){
        String jpql = "SELECT e FROM Exam e WHERE e.userTest.userId = :userId and e.examNum =:examNum";
        TypedQuery<Exam> query = em.createQuery(jpql, Exam.class);
        query.setParameter("userId", userId);
        query.setParameter("examNum", examNum);
        return query.getResultList();
    }
    public void saveMyVoca(MyVoca myVoca){
        em.persist(myVoca);
    }//내 단어장에 추가
    public void setExam(Exam exam) {em.persist(exam);}
    public  void setUserTest(UserTest userTest) {em.persist(userTest);}
    public void saveWrongAnswer(WrongAnswer wrongAnswer) {em.persist(wrongAnswer);}
    //public void doQuiz(id,)
}
