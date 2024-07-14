package com.example.certificate.controller;

import com.example.certificate.dto.VocaDto;
import com.example.certificate.entity.VocabularyList;
import com.example.certificate.service.VocaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
        @GetMapping (value = "/searchVoca")  //단어 제목으로 검색하는 컨트롤러
        public  ResponseEntity<VocaDto> searchVoca(@RequestParam("id") String id,
                                                   @RequestParam("title") String title){
            VocabularyList vocabularyList = null;
            try {
                List<VocabularyList> results = vocaService.searchTitle(id, title);
                if (results.isEmpty()) {
                    // 검색 결과가 없는 경우
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                vocabularyList = results.get(0);
            } catch (Exception e) {
                // 예외가 발생한 경우
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }


            VocaDto vocaDto = VocaDto.builder().
                    myVoca(vocabularyList.getMyVoca()).
                    myExplain(vocabularyList.getMyExplain()).
                    build();
            return  new ResponseEntity<>(vocaDto,HttpStatus.OK);
        }

        @GetMapping (value = "/getVoca")
        public ResponseEntity<List<VocaDto>> getVoca(@RequestParam("id") String id,
                                                     @RequestParam("page") int page) {
            // Assuming page size is 10, adjust as per your requirement
            int pageSize = 10;
            List<VocabularyList> vocabularyLists = vocaService.getMyVoca(id, page * pageSize);

            List<VocaDto> vocaDtoList = new ArrayList<>(vocabularyLists.size());
            for(VocabularyList  vocabularyList: vocabularyLists){
                VocaDto vocaDto = VocaDto.builder()
                        .myVoca(vocabularyList.getMyVoca())
                        .myExplain(vocabularyList.getMyExplain())
                        .build();
                vocaDtoList.add(vocaDto);
            }

            return new ResponseEntity<>(vocaDtoList, HttpStatus.OK);
        }
}
