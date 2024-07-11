package com.example.certificate.controller;

import com.example.certificate.entity.VocabularyList;
import com.example.certificate.service.VocaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VocaController {
        private final VocaService vocaService;
        @PostMapping (value = "/insertVoca")
        public void insertVoca( @RequestParam("voca") String voca,
                                @RequestParam("explain") String explain){
            VocabularyList vocabularyList = VocabularyList.builder().
                    userId("test").
                    myVoca(voca).
                    myExplain(explain).
                    build();
            vocaService.insertVoca(vocabularyList);
        }
}
