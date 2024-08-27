package com.example.certificate.service;


import com.example.certificate.ConvertDate;
import com.example.certificate.dto.ExamDto;
import com.example.certificate.dto.VocaDto;
import com.example.certificate.dto.WrongAnswerDto;
import com.example.certificate.entity.*;
import com.example.certificate.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final DelMyVocaRepository delMyVocaRepository;
    private final VocaRepository vocaRepository;
    private  final ChatGptService gptService;
    private  final WrongQuizRepository wrongQuizRepository;
    private final ExamRepository examRepository;
    public void saveMyVoca(String userId,String voca){
        MyVoca myVoca = MyVoca.builder().
                userId(userId).
                voca(voca).
                build();
        myVocaRepository.saveMyVoca(myVoca);
    }
    public void removeMyVoca(String id,String voca){
        delMyVocaRepository.deleteByUserIdAndVoca(id,voca);
    }
    public void setQuiz(String userId){
        List<MyVoca> myVocaList =  myVocaRepository.setQuiz(userId);
        ConvertDate convertDate = new ConvertDate(LocalDateTime.now());
        Long intDate = convertDate.intDate();
        QuizLog quizLog = QuizLog.builder()
                .userId(userId)
                .quizTime(intDate)
                .totalNum(myVocaList.size())
                .build();
        myVocaRepository.saveQuizLog(quizLog);
        int idx = 1;
        for (MyVoca myVoca:myVocaList){
            myVoca.setExamNum(idx++);
        }
    }
    public List<MyVoca> getAllExam(String userId){
       return myVocaRepository.setQuiz(userId);
    }
    public ExamDto doQuiz(String id, String num){
        MyVoca myVoca =  myVocaRepository.doMyVocaQuiz(id,Integer.valueOf(num)).get(0);
        ExamDto examDto = ExamDto.builder().
                examNum(String.valueOf(myVoca.getExamNum())).
                isImage(false).
                problem(myVoca.getVoca()).
                build();
        return examDto;
    }
    public void checkAnswer(String id,String userInput,String num){ //userInput은 사용자가 작성한 답안
        MyVoca myVoca =  myVocaRepository.doMyVocaQuiz(id,Integer.valueOf(num)).get(0);
        String voca  = myVoca.getVoca();
        String answer = vocaRepository.searchVoca(voca).get(0).getVocaExplain();
        String[] answerChat = gptService.recommend(voca,answer,userInput).split("!");

        if(answerChat[0].equals("오답")){
            QuizLog quizLog = examRepository.getRecentQuizLog(id);
            WrongQuiz wrongQuiz = WrongQuiz.builder().
                    quizLog(quizLog).
                    userId(id).
                    problem(voca).
                    commentary(answerChat[1]).
                    build();
            myVocaRepository.saveWrongQuiz(wrongQuiz);

        }
    }
    public List<WrongAnswerDto> wrongQuiz(String userId){
        List<WrongAnswerDto> wrongAnswerDtoList = new ArrayList<>();
        QuizLog quizLog = examRepository.getRecentQuizLog(userId);
        List<WrongQuiz >wrongQuizList = wrongQuizRepository.getUserWrongQuiz(quizLog);
        quizLog.updateWrongAnswerNum(wrongQuizList.size()); //오답문제 수 업데이트
        for(WrongQuiz wrongQuiz : wrongQuizList){
            WrongAnswerDto wrongAnswerDto = WrongAnswerDto.builder().
                    problem(wrongQuiz.getProblem()).
                    commentary(wrongQuiz.getCommentary()).
                    build();
            wrongAnswerDtoList.add(wrongAnswerDto);
        }

       return wrongAnswerDtoList;
    }
}
