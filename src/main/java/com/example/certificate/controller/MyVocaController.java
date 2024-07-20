package com.example.certificate.controller;


import com.example.certificate.entity.MyVoca;
import com.example.certificate.service.MyVocaService;
import com.example.certificate.service.VocaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyVocaController {
    private final MyVocaService myVocaService;
    private final VocaService vocaService;
    @PostMapping (value = "/saveMyVoca")
    public void saveMyVoca(@RequestParam("id") String id,
                           @RequestParam("voca") String voca){

        //단어로 기본키 찾음
        myVocaService.saveMyVoca(id,voca);
    }


}
