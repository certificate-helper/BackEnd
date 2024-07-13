package com.example.certificate.service;

import com.example.certificate.Encrypt;
import com.example.certificate.entity.User;
import com.example.certificate.repository.SignInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SignInService {
    private final SignInRepository signInRepository;
    public List<User> checkId(String id){
        return signInRepository.checkId(id);
    }
    public void registUser(String id, String pass){
        Encrypt en = new Encrypt(pass); // 비밀번호 암호화 해주는 클래스
        String encryptedPassword = en.getEncryptedPassword();
        User user = User.builder().
                userId(id).
                password(encryptedPassword).
                build();
        signInRepository.registUser(user);
    }
    public  List<User> logIn(String id, String pass) {
        Encrypt en = new Encrypt(pass); // 비밀번호ㄹ 암호화 해주는 클래스
        String encryptedPassword = en.getEncryptedPassword();
        return  signInRepository.logIn(id,encryptedPassword);}
}
