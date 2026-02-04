package com.example.musicapi.domain;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "regional")
public class Regional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer idExterno;

    @Column(length = 200, nullable = false)
    private String nome;

    private Boolean ativo;

    public Regional(Integer idExterno, String nome) {
        this.idExterno = idExterno;
        this.nome = nome;
        this.ativo = true;
    }

    protected Regional() {}
}

