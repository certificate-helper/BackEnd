package com.example.certificate.service;

import com.example.certificate.dto.VocaDto;
import com.example.certificate.entity.MyVoca;
import com.example.certificate.entity.VocabularyList;
import com.example.certificate.repository.MyVocaRepository;
import com.example.certificate.repository.VocaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VocaService {
    private final VocaRepository vocaRepository;
    private final MyVocaRepository myVocaRepository;
    public void insertVoca(VocabularyList vocabularyList){
        vocaRepository.insertVoca(vocabularyList);
    }

    @Transactional(readOnly = true)
    public List<VocaDto> getVoca(String id,int num){
        List<VocabularyList> vocabularyLists = vocaRepository.getVoca(num);
        List<VocaDto> vocaDtoList = new ArrayList<>(vocabularyLists.size());
        for(VocabularyList  vocabularyList: vocabularyLists){
            String voca = vocabularyList.getVoca();
            String myVoca = "null";
            List<MyVoca> myVocaList = myVocaRepository.getMyVoca(id,voca);
            if(!myVocaList.isEmpty()){ //내가 북마크 한 단어이면
                myVoca = myVocaList.get(0).getVoca();
            }
            VocaDto vocaDto = com.example.certificate.dto.VocaDto.builder()
                    .myVoca(myVoca)
                    .voca(voca)
                    .explain(vocabularyList.getVocaExplain())
                    .build();
            vocaDtoList.add(vocaDto);
        }

        return vocaDtoList;
    }
    @Transactional(readOnly = true)
    public List<VocabularyList> searchTitle(String title) { return vocaRepository.searchVoca(title);}

}

