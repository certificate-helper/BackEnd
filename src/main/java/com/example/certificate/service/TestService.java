package com.example.certificate.service;


import com.example.certificate.ConvertDate;
import com.example.certificate.ImageLoader;
import com.example.certificate.dto.ExamDto;
import com.example.certificate.entity.Exam;
import com.example.certificate.entity.ExamLog;
import com.example.certificate.entity.Test;
import com.example.certificate.repository.ExamRepository;
import com.example.certificate.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class TestService {
    private final TestRepository testRepository;
    private final ExamRepository examRepository;
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
    public void setExam(String userId,String year,String round){

        ConvertDate convertDate = new ConvertDate(LocalDateTime.now());
        int intDate = convertDate.intDate();
        ExamLog examLog = ExamLog.builder().
                userId(userId).
                examDate(intDate).
                build();
        examRepository.saveExamLog(examLog);

        List<Test> testList = testRepository.getExam(year, round);
        int idx = 1;
        for(Test test: testList){
            Exam exam = Exam.builder().
                    examLog(examLog).
                    problem(test.getProblem()).
                    answer(test.getAnswer()).
                    type(test.getType()).
                    category(test.getCategory()).
                    imageUrl(test.getImageUrl()).
                    examNum(idx++).
                    build();
            testRepository.setExam(exam);
        }
    }

    public ExamDto getExam(String userId,String num){
        Exam exam = examRepository.getRecentExam(userId,Integer.valueOf(num));
        boolean isImage = false;
        if (!exam.getImageUrl().equals("null")){
            isImage = true;
        }
        ImageLoader imageLoader = new ImageLoader(exam.getImageUrl()); //이미지 불러오는 클래스
        byte[] imageData = null;
        try {
           imageData = imageLoader.loadImage(); //이미지 저장
        }catch (Exception e){
            System.out.println("이미지 에러: "+e);
        }

        ExamDto examDto = ExamDto.builder().
                problem(exam.getProblem()).
                examNum(String.valueOf(exam.getExamNum())).
                isImage(isImage).
                imageData(imageData).
                build();
        return examDto;
    }

}
