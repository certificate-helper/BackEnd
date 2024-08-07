package com.example.certificate.repository;

import com.example.certificate.entity.MyVoca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelMyVocaRepository extends JpaRepository<MyVoca, Long> {
    // 내 단어장에서 단어를 삭제하는 리포지토리
    void deleteByUserIdAndVoca(String userId, String voca);
}
