package com.example.certificate.service;

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

    }
}
