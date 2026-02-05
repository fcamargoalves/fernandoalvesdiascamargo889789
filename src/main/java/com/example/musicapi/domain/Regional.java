package com.example.musicapi.domain;

import jakarta.persistence.*;
import jakarta.persistence.*;

@Entity
@Table(name = "regional")
public class Regional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_externo", nullable = false)
    private Integer idExterno;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(nullable = false)
    private boolean ativo = true;

    protected Regional() {
        // JPA
    }

    public Regional(Integer idExterno, String nome) {
        this.idExterno = idExterno;
        this.nome = nome;
        this.ativo = true;
    }

    // ===== GETTERS =====

    public Long getId() {
        return id;
    }

    public Integer getIdExterno() {
        return idExterno;
    }

    public String getNome() {
        return nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    // ===== SETTERS =====

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
