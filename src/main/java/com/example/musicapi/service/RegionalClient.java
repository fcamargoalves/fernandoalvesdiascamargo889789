package com.example.musicapi.service;

import com.example.musicapi.dto.RegionalDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class RegionalClient {

    private final RestTemplate restTemplate;

    public RegionalClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<RegionalDTO> buscarRegionais() {
        ResponseEntity<RegionalDTO[]> response =
                restTemplate.getForEntity(
                        "https://integrador-argus-api.geia.vip/v1/regionais",
                        RegionalDTO[].class
                );

        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }
}

