package com.example.certificate.service;


import com.example.certificate.dto.AnswerRateDto;
import com.example.certificate.entity.QuizLog;
import com.example.certificate.repository.StatisticRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepository statisticRepository;

    public AnswerRateDto getUserQuizLog(String userId){
        List<QuizLog> quizLogList =  statisticRepository.getUserQuizLog(userId);
        double totalNum = 0.0;
        double wrongNum = 0.0 ;
        for(QuizLog quizLog:quizLogList){
            totalNum += quizLog.getTotalNum();
            wrongNum += quizLog.getWrongAnswerNum();
        }
        AnswerRateDto answerRateDto = AnswerRateDto.builder().
                answerRate(String.valueOf(wrongNum/totalNum*100)). //
                build();
        return answerRateDto;
    }
}
