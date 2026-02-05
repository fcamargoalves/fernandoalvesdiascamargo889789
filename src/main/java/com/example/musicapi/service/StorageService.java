package com.example.musicapi.service;

import java.io.InputStream;

public interface StorageService {

    void upload(
            String bucket,
            String objectName,
            InputStream inputStream,
            String contentType
    );

    String gerarUrlPreAssinada(
            String bucket,
            String objectName,
            int expiracaoEmMinutos
    );
}

