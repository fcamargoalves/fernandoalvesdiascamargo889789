package com.example.musicapi.service;

import com.example.musicapi.domain.Album;
import com.example.musicapi.repository.AlbumRepository;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    private final AlbumRepository repository;
    private final StorageService storageService;
    private final AlbumNotificationService notificationService;

    public AlbumService(AlbumRepository repository,
                        StorageService storageService,
                        AlbumNotificationService notificationService) {
        this.repository = repository;
        this.storageService = storageService;
        this.notificationService = notificationService;
    }

    public Album salvar(Album album) {
        Album saved = repository.save(album);
        notificationService.notificarNovoAlbum(saved.getTitulo());
        return saved;
    }
}
