package com.example.certificate.service;


import com.example.certificate.dto.ExamDto;
import com.example.certificate.entity.Exam;
import com.example.certificate.entity.MyVoca;
import com.example.certificate.entity.UserTest;
import com.example.certificate.entity.VocabularyList;
import com.example.certificate.repository.MyVocaRepository;
import com.example.certificate.repository.VocaRepository;
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
    private final VocaRepository vocaRepository;
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
        myVocaRepository.setUserTest(userTest);
        int idx = 1;
        for (MyVoca myVoca:myVocaList){
            String voca = myVoca.getVoca();
            VocabularyList vocabulary = vocaRepository.searchVoca(voca).get(0);
            Exam exam = Exam.builder().
                    userTest(userTest).
                    vocabularyList(vocabulary).
                    examNum(idx++).
                    state(0).
                    build();
            myVocaRepository.setExam(exam);
        }
    }
    public List<Exam> getAllExam(String userId){
       return myVocaRepository.getAllExam(userId);
    }
    public ExamDto doQuiz(String id, String num){
        Exam exam = myVocaRepository.doQuiz(id,Integer.valueOf(num)).get(0);
        ExamDto examDto = ExamDto.builder().
                examNum(String.valueOf(exam.getExamNum())).
                problem(exam.getVocabularyList().getVocaExplain()).
                build();
        return examDto;
    }
    public void checkAnswer(String id,String voca,String num){
        Exam exam = myVocaRepository.doQuiz(id,Integer.valueOf(num)).get(0);

    }
}
