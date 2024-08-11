package com.example.certificate;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageLoader {
    private String imageUrl;
    public  ImageLoader(String imageUrl){
        this.imageUrl = imageUrl;
    }
    public byte[] loadImage() throws IOException {
        System.out.println("이미지 url: "+ imageUrl);
        // 리소스 파일을 불러옵니다.
        Path path = Paths.get(imageUrl);
        return Files.readAllBytes(path);
    }
}