package com.example.musicapi;

import com.example.musicapi.domain.Album;
import com.example.musicapi.repository.AlbumRepository;
import com.example.musicapi.service.AlbumNotificationService;
import com.example.musicapi.service.AlbumService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {

    @Mock
    private AlbumRepository repository;

    @Mock
    private StorageService storageService;

    @Mock
    private AlbumNotificationService notificationService;

    @InjectMocks
    private AlbumService service;

    @Test
    void deveSalvarAlbumENotificarWebSocket() {
        Album album = new Album();
        album.setTitulo("Hybrid Theory");

        when(repository.save(any(Album.class))).thenReturn(album);

        Album salvo = service.salvar(album);

        assertEquals("Hybrid Theory", salvo.getTitulo());
        verify(notificationService).notificarNovoAlbum("Hybrid Theory");
    }
}

