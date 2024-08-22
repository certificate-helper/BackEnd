package com.example.certificate.service;


import com.example.certificate.dto.AnswerRateDto;
import com.example.certificate.dto.CategoryCoutDto;
import com.example.certificate.dto.QuizFrequencyDto;
import com.example.certificate.entity.ExamLog;
import com.example.certificate.entity.QuizLog;
import com.example.certificate.entity.WrongAnswer;
import com.example.certificate.repository.ExamRepository;
import com.example.certificate.repository.StatisticRepository;
import com.example.certificate.repository.WrongAnswerRepository;
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
    private  final WrongAnswerRepository wrongAnswerRepository;
    private  final ExamRepository examRepository;
    public AnswerRateDto getUserQuizLog(String userId){
        List<QuizLog> quizLogList =  statisticRepository.getUserQuizLog(userId);
        double totalNum = 0.0;
        double wrongNum = 0.0 ;
        for(QuizLog quizLog:quizLogList){
            totalNum += quizLog.getTotalNum();
            wrongNum += quizLog.getWrongAnswerNum();
        }
        String answerRate = String.valueOf(wrongNum/totalNum*100);
        if(answerRate.length()>4){
            answerRate = answerRate.substring(0,4);
        }
        AnswerRateDto answerRateDto = AnswerRateDto.builder().
                answerRate(answerRate). //
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

    public  List<CategoryCoutDto>getCategoryTrend(){
        List<CategoryCoutDto> categoryCoutDtoList = new ArrayList<>();
        List<Object[]> results = statisticRepository.getCategoryTrend();
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
    public  List<AnswerRateDto> getUserTestRate(String userId){ //사용자가 친 시험당 정답률
            List<Object[]> wrongAnswerList =  wrongAnswerRepository.getUserWrongAnswerCount(userId);
            List<AnswerRateDto> answerRateDtoList = new ArrayList<>(wrongAnswerList.size());

//        for (Object[] result : wrongAnswerList) {
//            ExamLog examLog = (ExamLog) result[0];
//            Long count = (Long) result[1];
//            System.out.println("ExamLog: " + examLog + ", Count: " + count);
//        }
            for(Object[] wrongAnswer:wrongAnswerList){
                Long count = (Long)wrongAnswer[1];
                AnswerRateDto answerRateDto = AnswerRateDto.builder().
                        answerRate(String.valueOf(20-count.intValue())).
                        build();

                answerRateDtoList.add(answerRateDto);
            }
        return answerRateDtoList;
    }
}
