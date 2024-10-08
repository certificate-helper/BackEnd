package com.example.certificate.controller;


import com.example.certificate.entity.User;
import com.example.certificate.service.SignInService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
            System.out.println("id: "+ id);
            List<User> user = signInService.checkId(id);
            if(user.size() != 0){  //중복된 아이디가 있으면
                return new ResponseEntity<>("no", HttpStatus.OK);
            }
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }


    @PostMapping(value = "/signUp") //회원가입
        public ResponseEntity<String> signUp(@RequestParam("id") String id,
                                            @RequestParam("pass") String pass
        )
        {
            System.out.println("id: "+ id);
            try {
                signInService.registUser(id,pass);
            }catch (Exception e){
                return new ResponseEntity<>("no", HttpStatus.OK);
            }
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }

    @GetMapping(value = "/signIn")  //로그인
    public   ResponseEntity<String> signIn(@RequestParam("id") String id,
                                           @RequestParam("pass") String pass,
                                           HttpServletRequest request){
            System.out.println("id: "+ id);
        List<User> user = signInService.logIn(id,pass);
        if(user.size() != 0){
            HttpSession session = request.getSession();
            session.setAttribute("user", id);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("no", HttpStatus.OK);

    }
    @GetMapping(value = "/qqqq")
    public String asd(){
            return "a";
    }
}
