package com.example.certificate.service;


import com.example.certificate.dto.WrongAnswerDto;
import com.example.certificate.entity.Exam;
import com.example.certificate.entity.ExamLog;
import com.example.certificate.repository.ExamRepository;
import com.example.certificate.repository.WrongAnswerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WrongAnswerService {
    private final WrongAnswerRepository wrongAnswerRepository;
    private final ExamRepository examRepository;
    public List<WrongAnswerDto> getWrongTest(String userId){
        List<WrongAnswerDto> wrongAnswerDtoList = new ArrayList<>();
        ExamLog examLog = examRepository.getRecentExamLog(userId);
        List<Exam> examList = examRepository.getRecentWrongAnswer(examLog);
        for(Exam exam :examList){
            WrongAnswerDto wrongAnswerDto=  WrongAnswerDto.builder().
                    answer(exam.getAnswer()).
                    problem(exam.getProblem()).
                    build();

            wrongAnswerDtoList.add(wrongAnswerDto);
        }
        return wrongAnswerDtoList;
    }
}
