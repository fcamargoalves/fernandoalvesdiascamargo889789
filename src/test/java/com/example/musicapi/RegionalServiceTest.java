package com.example.musicapi;

import com.example.musicapi.domain.Regional;
import com.example.musicapi.dto.RegionalDTO;
import com.example.musicapi.repository.RegionalRepository;
import com.example.musicapi.service.RegionalClient;
import com.example.musicapi.service.RegionalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegionalServiceTest {

    @Mock
    private RegionalRepository repository;

    @Mock
    private RegionalClient client;

    @InjectMocks
    private RegionalService service;

    @Test
    void deveInserirNovaRegionalQuandoNaoExistir() {
        RegionalDTO dto = new RegionalDTO(1, "Norte");

        when(client.buscarRegionais()).thenReturn(List.of(dto));
        when(repository.findByIdExternoAndAtivoTrue(1)).thenReturn(Optional.empty());
        when(repository.findAllByAtivoTrue()).thenReturn(List.of());

        service.sincronizar();

        verify(repository).save(argThat(regional ->
                regional.getIdExterno().equals(1) &&
                        regional.getNome().equals("Norte") &&
                        regional.getAtivo()
        ));
    }

    @Test
    void deveInativarRegionalAusenteNoEndpoint() {
        Regional existente = new Regional(2, "Sul");

        when(client.buscarRegionais()).thenReturn(List.of());
        when(repository.findAllByAtivoTrue()).thenReturn(List.of(existente));

        service.sincronizar();

        assertFalse(existente.getAtivo());
    }

    @Test
    void deveVersionarQuandoNomeForAlterado() {
        Regional antiga = new Regional(3, "Centro");
        RegionalDTO atualizada = new RegionalDTO(3, "Centro-Oeste");

        when(client.buscarRegionais()).thenReturn(List.of(atualizada));
        when(repository.findByIdExternoAndAtivoTrue(3)).thenReturn(Optional.of(antiga));
        when(repository.findAllByAtivoTrue()).thenReturn(List.of(antiga));

        service.sincronizar();

        assertFalse(antiga.getAtivo());
        verify(repository).save(argThat(r ->
                r.getNome().equals("Centro-Oeste") &&
                        r.getAtivo()
        ));
    }
}
