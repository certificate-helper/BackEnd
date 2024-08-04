package com.example.certificate.controller;

import com.example.certificate.service.ChatGptService;
import io.github.flashvayne.chatgpt.dto.ChatRequest;
import io.github.flashvayne.chatgpt.dto.ChatResponse;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor

public class ChatGptController {

    private  final ChatGptService gptService;


    @PostMapping  (value = "/gpttest")
    public String Q(){
        List<String> a = new ArrayList<>();
        a.add("곱창");
        a.add("간장");
        a.add("마늘");
        a.add("두부");

        gptService.recommend(a);

        return "q";
    }
}
