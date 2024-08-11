package com.example.certificate.repository;


import com.example.certificate.entity.Exam;
import com.example.certificate.entity.Test;
import com.example.certificate.entity.UserTest;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TestRepository {
    private final EntityManager em;
    public  void persitData(Test test){
        em.persist(test);
    }
    public  void persitUserTest(UserTest userTest){em.persist(userTest);}
    public void insertTest(Test test) {em.persist(test);}

    public void setExam(Exam exam) {em.persist(exam);}
    public List<Test> getExam(String year, String round){ //시험을 치기위해 기출문제를 순서를 섞어서 불러오기
        return em.createQuery("SELECT m FROM Test m WHERE m.year =:year and m.round =:round ORDER BY RAND()" , Test.class)
                .setParameter("year", year)
                .setParameter("round",round)
                .getResultList();
    }

    public  List<Exam> doExam(int num){
        return em.createQuery("SELECT m FROM Exam m WHERE m.num =:num" , Exam.class)
                .setParameter("num",num)
                .getResultList();
    }
}
