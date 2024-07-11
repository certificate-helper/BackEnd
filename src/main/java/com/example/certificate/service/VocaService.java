package com.example.certificate.service;

import com.example.certificate.entity.VocabularyList;
import com.example.certificate.repository.VocaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VocaService {
    private final VocaRepository vocaRepository;
    public void insertVoca(VocabularyList vocabularyList){
        vocaRepository.insertVoca(vocabularyList);
    }
}
