package com.example.certificate.controller;


import com.example.certificate.entity.User;
import com.example.certificate.service.SignInService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController  //그냥 Controller를 사용하면 상태코드 404를 반환한다.
@RequiredArgsConstructor
public class SignInController {
        private final SignInService signInService;

        @GetMapping(value = "/checkId")  //중복된 아이디가 존재하는지 검사
        public   ResponseEntity<String> checkId(@RequestParam("id") String id){
            List<User> user = signInService.checkId(id);
            if(user.size() != 0){  //중복된 아이디가 있으면
                return new ResponseEntity<>("no", HttpStatus.OK);
            }
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }

        @PostMapping(value = "/signIn") //회원가입
        public ResponseEntity<String> signIn(@RequestParam("id") String id,
                                            @RequestParam("pass") String pass
                                            ///,@RequestParam("test") List<String> test
        )
        {
            System.out.println("id: "+id);
            System.out.println("pass: "+pass);
            try {
                signInService.registUser(id,pass);
            }catch (Exception e){
                return new ResponseEntity<>("no", HttpStatus.OK);
            }
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
}
