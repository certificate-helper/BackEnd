package com.example.certificate.service;


import com.example.certificate.entity.MyVoca;
import com.example.certificate.repository.MyVocaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyVocaService {
    private final MyVocaRepository myVocaRepository;
    public void saveMyVoca(String userId,String voca){
        MyVoca myVoca = MyVoca.builder().
                userId(userId).
                voca(voca).
                build();
        myVocaRepository.saveMyVoca(myVoca);
    }
}
