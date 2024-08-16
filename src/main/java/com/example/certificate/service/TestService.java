package com.example.certificate.service;


import com.example.certificate.ConvertDate;
import com.example.certificate.ImageLoader;
import com.example.certificate.StringConverter;
import com.example.certificate.dto.ExamDto;
import com.example.certificate.entity.Exam;
import com.example.certificate.entity.ExamLog;
import com.example.certificate.entity.Test;
import com.example.certificate.entity.WrongAnswer;
import com.example.certificate.repository.ExamRepository;
import com.example.certificate.repository.MyVocaRepository;
import com.example.certificate.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class TestService {
    private final TestRepository testRepository;
    private final ExamRepository examRepository;
    private  final ChatGptService gptService;
    private final MyVocaRepository myVocaRepository;
    public void insertTest(int year, int round, String type, String problem,
                           String answer, String category, MultipartFile image){
        Test test = Test.builder().
                year(year).
                round(round).
                problem(problem).
                answer(answer).
                type(type).
                category(category).
                build();

        if (image != null && !image.isEmpty())  {
            String uploadDir = "/Users/joseungbin/Downloads/certificate/src/main/java/com/example/certificate/image/";
            String fileName = image.getOriginalFilename();
            File saveFile = new File(uploadDir + fileName);
            try {
                image.transferTo(saveFile);
                test.saveImageUrl(uploadDir + fileName);
            }catch (Exception e){
                System.out.println("이미지 저장중 에러발생: "+e);
            }
        }
        testRepository.insertTest(test);
    }
    public void setExam(String userId,String year,String round){

        ConvertDate convertDate = new ConvertDate(LocalDateTime.now());
        Long intDate = convertDate.intDate();
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
                    year(test.getYear()).
                    round(test.getRound()).
                    build();
            testRepository.setExam(exam);
        }
    }

    public ExamDto getExam(String userId,String num){
        //사용자 친 기출시험 중 제일 최근기록을 불러온다.
        ExamLog examLog = examRepository.getRecentExamLog(userId);

        Exam exam = examRepository.getRecentExam(examLog,Integer.valueOf(num));
        boolean isImage = false;
        if (!exam.getImageUrl().equals("null")){ //기출문제에 이미지가 있으면
            isImage = true;
        }
        ImageLoader imageLoader = new ImageLoader(exam.getImageUrl()); //이미지 불러오는 클래스
        byte[] imageData = null;
        if(isImage) { //기출문제에 이미지가 있으면 이미지 데이터를 삽입
            try {
                imageData = imageLoader.loadImage(); //이미지 저장
            } catch (Exception e) {
                System.out.println("이미지 에러: " + e);
            }
        }
        ExamDto examDto = ExamDto.builder().
                problem(exam.getProblem()).
                examNum(String.valueOf(exam.getExamNum())).
                isImage(isImage).
                imageData(imageData).
                build();
        return examDto;
    }
    public void checkExamAnswer(String userId, String userInput,String num){
        ExamLog exam_log = examRepository.getRecentExamLog(userId);
        Exam exam = examRepository.getRecentExam(exam_log,Integer.valueOf(num));
        if(exam.getType().equals("long")){  //주관형 단답형일 때
            String[] answerChat = gptService.recommend(exam.getProblem(),exam.getAnswer(),userInput).split("!");
            System.out.println("gpt 리턴값: "+answerChat);
            if(answerChat[0].equals("정답")){
                exam.updateAnswerCheck("O");
            }else{
                exam.updateAnswerCheck("X");
                ExamLog examLog = examRepository.getRecentExamLog(userId);
                WrongAnswer wrongAnswer = WrongAnswer.builder().
                        examLog(examLog).
                        userId(userId).
                        problem(exam.getProblem()).
                        commentary(answerChat[1]).
                        year(exam.getYear()).
                        round(exam.getRound()).
                        category(exam.getCategory()).

                        build();
                myVocaRepository.saveWrongAnswer(wrongAnswer);
            }
        }else{ // 단답형
            String[] answerList = userInput.replaceAll(" ","").split(",");  //복수정답은  ,를 기준으로 분리
            StringConverter stringConverter = new StringConverter(); // 대문자로 치환해 주는 클래스 객체 생성
            for (String userAnswer :answerList ){
                userAnswer = stringConverter.convertToUpperCase(userAnswer); //사용자 답안을 영문 대문자로 치환
                String examAnswer  = stringConverter.convertToUpperCase(exam.getAnswer().replaceAll(" ",""));//정답을 영문 대문자로 치환
                if(examAnswer.contains(userAnswer)){ //정답이면
                    exam.updateAnswerCheck("O");
                }else{ //

                    exam.updateAnswerCheck("X");
                    ExamLog examLog = examRepository.getRecentExamLog(userId);
                    WrongAnswer wrongAnswer = WrongAnswer.builder().
                            examLog(examLog).
                            userId(userId).
                            problem(exam.getProblem()).
                            commentary(exam.getAnswer()).
                            year(exam.getYear()).
                            round(exam.getRound()).
                            category(exam.getCategory()).
                            //testType("exam").
                                    build();
                    myVocaRepository.saveWrongAnswer(wrongAnswer);

                }
            }

        }
    }
}
