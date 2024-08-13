package com.example.certificate.service;


import com.example.certificate.dto.AnswerRateDto;
import com.example.certificate.dto.QuizFrequencyDto;
import com.example.certificate.entity.QuizLog;
import com.example.certificate.repository.StatisticRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<QuizFrequencyDto> getProblemFrequency(String userId){
        List<QuizFrequencyDto> quizFrequencyDtoList = new ArrayList<>();
        List<Object[]> results =  statisticRepository.getProblemFrequency(userId);
        for (Object[] result : results) {
            String word = (String) result[0];
            Long count = (Long) result[1];

            QuizFrequencyDto quizFrequencyDto = QuizFrequencyDto.builder()
                    .word(word)
                    .count(String.valueOf(count))
                    .build();
            quizFrequencyDtoList.add(quizFrequencyDto);
        }
        return  quizFrequencyDtoList;
    }

    public List<AnswerRateDto> getQuizAnswerNum(String userId){
        List<AnswerRateDto> answerRateDtoList = new ArrayList<>();
        List<QuizLog> quizLogList =  statisticRepository.getUserQuizLog(userId);
        for( QuizLog quizLog:quizLogList){
            AnswerRateDto answerRateDto = AnswerRateDto.builder().
                    answerRate(String.valueOf(quizLog.getTotalNum()-quizLog.getWrongAnswerNum())). //
                            build();
            answerRateDtoList.add(answerRateDto);
        }
        return answerRateDtoList;
    }
}
