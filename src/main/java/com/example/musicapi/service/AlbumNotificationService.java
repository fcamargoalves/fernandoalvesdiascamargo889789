package com.example.musicapi.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlbumNotificationService {

    private final SimpMessagingTemplate template;

    public AlbumNotificationService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void notificarNovoAlbum(String titulo) {
        template.convertAndSend("/topic/albums", titulo);
    }
}

