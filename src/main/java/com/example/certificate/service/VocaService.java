package com.example.certificate.service;

import com.example.certificate.entity.VocabularyList;
import com.example.certificate.repository.VocaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VocaService {
    private final VocaRepository vocaRepository;
    public void insertVoca(VocabularyList vocabularyList){
        vocaRepository.insertVoca(vocabularyList);
    }

    @Transactional(readOnly = true)
    public List<VocabularyList> getMyVoca(String id, int num){
        return vocaRepository.getMyVoca(id,num);
    }
    @Transactional(readOnly = true)
    public List<VocabularyList> searchTitle(String userId,String title) { return vocaRepository.searchVoca(userId,title);}

}
