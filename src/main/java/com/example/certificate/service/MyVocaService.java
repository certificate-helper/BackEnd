package com.example.certificate.service;


import com.example.certificate.dto.ExamDto;
import com.example.certificate.dto.VocaDto;
import com.example.certificate.dto.WrongAnswerDto;
import com.example.certificate.entity.*;
import com.example.certificate.repository.DelMyVocaRepository;
import com.example.certificate.repository.MyVocaRepository;
import com.example.certificate.repository.VocaRepository;
import com.example.certificate.repository.WrongAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    private  final WrongAnswerRepository wrongAnswerRepository;
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
    public void setQuiz(String id){
        List<MyVoca> myVocaList =  myVocaRepository.setQuiz(id);
//        UserTest userTest = getUserTest(id);
//        myVocaRepository.setUserTest(userTest);
        int idx = 1;
        for (MyVoca myVoca:myVocaList){

            myVoca.setExamNum(idx++);
//            String voca = myVoca.getVoca();
//            VocabularyList vocabulary = vocaRepository.searchVoca(voca).get(0);
//            Exam exam = Exam.builder().
//                    userTest(userTest).
//                    vocabularyList(vocabulary).
//                    examNum(idx++).
//                    state(0).
//                    build();
//            myVocaRepository.setExam(exam);
        }
    }
    public List<MyVoca> getAllExam(String userId){
       return myVocaRepository.setQuiz(userId);
    }
    public ExamDto doQuiz(String id, String num){
        MyVoca myVoca =  myVocaRepository.doMyVocaQuiz(id,Integer.valueOf(num)).get(0);
//        Exam exam = myVocaRepository.doQuiz(id,Integer.valueOf(num)).get(0);
        ExamDto examDto = ExamDto.builder().
                examNum(String.valueOf(myVoca.getExamNum())).
                //problem(exam.getVocabularyList().getVocaExplain()).
                problem(myVoca.getVoca()).
                build();
        return examDto;
    }
    public void checkAnswer(String id,String userInput,String num){ //userInput은 사용자가 작성한 답안
        //Exam exam = myVocaRepository.doQuiz(id,Integer.valueOf(num)).get(0);
        MyVoca myVoca =  myVocaRepository.doMyVocaQuiz(id,Integer.valueOf(num)).get(0);
        String voca  = myVoca.getVoca();
        String answer = vocaRepository.searchVoca(voca).get(0).getVocaExplain();
        String[] answerChat = gptService.recommend(voca,answer,userInput).split("!");
        if(answerChat[0].equals("오답")){
            WrongAnswer wrongAnswer = WrongAnswer.builder().
                    userId(id).
                    problem(voca).
                    commentary(answerChat[1]).
                    build();
            myVocaRepository.saveWrongAnswer(wrongAnswer);
        }
//        if (exam.getVocabularyList().getVoca().equals(voca)) { //맞으면
//            exam.updateAnswerCheck(1); //1로 update
//            System.out.println("정답");
//        }
//        else{
//            exam.updateAnswerCheck(-1); //-1로 update
//            System.out.println("오답");
//        }
    }
    public List<WrongAnswerDto> wrongAnswer(String id){
        List<WrongAnswer> wrongAnswerList = wrongAnswerRepository.getUserWrongAnswer(id);
        List<WrongAnswerDto> wrongAnswerDtoList = new ArrayList<>();
        for(WrongAnswer wrongAnswer:wrongAnswerList){

            WrongAnswerDto wrongAnswerDto = WrongAnswerDto.builder().
                    problem(wrongAnswer.getProblem()).
                    //answer(wrongAnswer.).
                    commentary(wrongAnswer.getCommentary()).
                    build();
            wrongAnswerDtoList.add(wrongAnswerDto);
        }
       return wrongAnswerDtoList;
    }
}
