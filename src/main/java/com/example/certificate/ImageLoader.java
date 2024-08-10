package com.example.certificate;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

public class ImageLoader {
    private String imageUrl;
    public  ImageLoader(String imageUrl){
        this.imageUrl = imageUrl;
    }
    public byte[] loadImage() throws IOException {
        // 리소스 파일을 불러옵니다.
        ClassPathResource imgFile = new ClassPathResource(imageUrl);

        // InputStream으로 파일을 읽어와 byte[]로 변환합니다.
        try (InputStream inputStream = imgFile.getInputStream()) {
            return StreamUtils.copyToByteArray(inputStream);
        }
    }
}