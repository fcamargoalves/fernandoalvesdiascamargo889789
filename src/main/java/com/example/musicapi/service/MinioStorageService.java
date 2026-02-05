package com.example.musicapi.service;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Service
public class MinioStorageService implements StorageService {

    private final MinioClient minioClient;

    public MinioStorageService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public void upload(
            String bucket,
            String objectName,
            InputStream inputStream,
            String contentType
    ) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectName)
                            .stream(inputStream, -1, 10 * 1024 * 1024)
                            .contentType(contentType)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar arquivo para o MinIO", e);
        }
    }

    @Override
    public String gerarUrlPreAssinada(
            String bucket,
            String objectName,
            int expiracaoEmMinutos
    ) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object(objectName)
                            .expiry(expiracaoEmMinutos, TimeUnit.MINUTES)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar URL pré-assinada", e);
        }
    }
}

