package com.example.certificate.service;


import com.example.certificate.entity.Exam;
import com.example.certificate.entity.Test;
import com.example.certificate.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class TestService {
    private final TestRepository testRepository;
    public void insertTest(){
        Test test = Test.builder().
                year(2022).
                round(1).
                answer("q").
                problem("w").
                type("E").
                build();
        testRepository.persitData(test);
    }
    public void setExam(Exam exam){ testRepository.setExam(exam);}
    public List<Test> getExam(String year,String round){
        return testRepository.getExam(year, round);
    }
}
