package com.example.certificate.service;



import com.example.certificate.dto.ChatRequest;
import com.example.certificate.dto.ChatResponse;
import com.example.certificate.dto.Message;
import com.example.certificate.entity.WrongAnswer;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service

public class ChatGptService {



    @Value("${openai.model.chat}")
    private String chatModel ;

    @Value("${openai.api.url.chat}")
    private String chatApiURL;

    @Autowired
    private RestTemplate template;


    public String chat(String prompt){
        ChatRequest request = new ChatRequest(chatModel, prompt);


        try {
            ChatResponse response  = template.postForObject(chatApiURL, request, ChatResponse.class);
             return response.getChoices().get(0).getMessage().getContent();
        }catch (Exception e){
            System.out.println("gpt에러: "+e);
        }
        return "w";
    }
    public String recommend(String voca,String answer,String userInput){
        String answerPrompt ="나는 서술형 주관식 문제에서 " +
                "사용자가 입력한 정답의 여부를 너에게 판단시키고 싶어." +
                voca+ "이란 용어 대한 올바른 설명은 "+answer+"야."+
                "사용자가 입력한 서술형 답안은 "+userInput+"만약 너가 판단했을 때 맞으면 '정답!'이라고 말해주고 틀렸으면 '오답!'이라고" +
                "말해주고 틀린이유 또한 뒤에 설명해줘." +"반든시 맨처음 단어는 정답! 또는 오답!으로 시작해야하고 오답! 일 경우 틀린 이유까지 서술해줘야해"
                ;
        return chat(answerPrompt);
    }
}
