package com.example.certificate.service;


import com.example.certificate.entity.Exam;
import com.example.certificate.entity.MyVoca;
import com.example.certificate.entity.UserTest;
import com.example.certificate.repository.MyVocaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyVocaService {
    public UserTest getUserTest(String userId){
        LocalDate now = LocalDate.now();
        UserTest userTest = UserTest.
                builder().
                userId(userId). //유저 아이디
                        testDate(now).  //오늘 날짜
                        build();
        return userTest;
    }
    private final MyVocaRepository myVocaRepository;
    public void saveMyVoca(String userId,String voca){
        MyVoca myVoca = MyVoca.builder().
                userId(userId).
                voca(voca).
                build();
        myVocaRepository.saveMyVoca(myVoca);
    }
    public void setQuiz(String id){
        List<MyVoca> myVocaList =  myVocaRepository.setQuiz(id);
        UserTest userTest = getUserTest(id);
        int idx = 1;
        for (MyVoca myVoca:myVocaList){
            Exam exam = Exam.builder().
                    userTest(userTest).
                    myVoca(myVoca).
                    examNum(idx++).
                    state(0).
                    build();
            myVocaRepository.setExam(exam);
        }
    }
}
