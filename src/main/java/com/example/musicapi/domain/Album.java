package com.example.musicapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String capaPath; // caminho no MinIO

    @ManyToMany(mappedBy = "albums")
    private Set<Artist> artists = new HashSet<>();
}
