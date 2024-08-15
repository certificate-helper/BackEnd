package com.example.certificate.service;


import com.example.certificate.dto.AnswerRateDto;
import com.example.certificate.dto.CategoryCoutDto;
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
    //틀린 단어 빈도수를 리턴해주는 함수
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
    //각 퀴즈 회차당 정답횟수를 리턴해주는 함수
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

    public  List<CategoryCoutDto> getCategoryCountsByUserId(String userId){
        List<CategoryCoutDto> categoryCoutDtoList = new ArrayList<>();
        List<Object[]> results = statisticRepository.getCategoryCountsByUserId(userId);
        for (Object[] result : results) {
            String category = (String) result[0];
            Long count = (Long) result[1];

            CategoryCoutDto categoryCoutDto = CategoryCoutDto.builder().
                    category(category).
                    count(String.valueOf(count)).
                    build();

            categoryCoutDtoList.add(categoryCoutDto);
        }

        return  categoryCoutDtoList;
    }
}
