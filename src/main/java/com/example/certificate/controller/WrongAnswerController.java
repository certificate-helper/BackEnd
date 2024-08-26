package com.example.certificate.controller;


import com.example.certificate.dto.WrongAnswerDto;
import com.example.certificate.service.WrongAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WrongAnswerController {
    private final WrongAnswerService wrongAnswerService;

    @GetMapping (value = "/wrong-test")
    public List<WrongAnswerDto> getWrongTest(@RequestParam("id") String id){ //기출문제 응시 후 틀린 문제들을 주는 api
        return wrongAnswerService.getWrongTest(id);
    }
}
