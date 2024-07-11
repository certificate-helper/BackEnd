package com.example.certificate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class DataInsertController {
    @GetMapping(value = "/")
    public  String dew(){
        return "test";
    }
}
