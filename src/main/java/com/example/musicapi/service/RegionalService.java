package com.example.musicapi.service;

import com.example.musicapi.domain.Regional;
import com.example.musicapi.dto.RegionalDTO;
import com.example.musicapi.repository.RegionalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegionalService {

    private final RegionalRepository repository;
    private final RegionalClient client;

    public RegionalService(RegionalRepository repository, RegionalClient client) {
        this.repository = repository;
        this.client = client;
    }

    @Transactional
    public void sincronizar() {
        List<RegionalDTO> externas = client.buscarRegionais();

        Map<Integer, RegionalDTO> externasMap = externas.stream()
                .collect(Collectors.toMap(RegionalDTO::id, r -> r));

        // 1️⃣ Inativar ausentes
        repository.findAllByAtivoTrue().forEach(regional -> {
            if (!externasMap.containsKey(regional.getIdExterno())) {
                regional.setAtivo(false);
            }
        });

        // 2️⃣ Inserir novas ou tratar alterações
        externas.forEach(dto -> {
            Optional<Regional> existente =
                    repository.findByIdExternoAndAtivoTrue(dto.id());

            if (existente.isEmpty()) {
                repository.save(new Regional(dto.id(), dto.nome()));
            } else if (!existente.get().getNome().equals(dto.nome())) {
                Regional antiga = existente.get();
                antiga.setAtivo(false);
                repository.save(new Regional(dto.id(), dto.nome()));
            }
        });
    }

    public List<Regional> listarAtivas() {
        return repository.findAllByAtivoTrue();
    }
}

