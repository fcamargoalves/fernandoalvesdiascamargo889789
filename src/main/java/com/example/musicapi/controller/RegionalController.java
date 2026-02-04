package com.example.musicapi.controller;

import com.example.musicapi.domain.Regional;
import com.example.musicapi.service.RegionalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/regionais")
public class RegionalController {

    private final RegionalService service;

    public RegionalController(RegionalService service) {
        this.service = service;
    }

    @PostMapping("/sincronizar")
    public ResponseEntity<Void> sincronizar() {
        service.sincronizar();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Regional> listar() {
        return service.listarAtivas();
    }
}

