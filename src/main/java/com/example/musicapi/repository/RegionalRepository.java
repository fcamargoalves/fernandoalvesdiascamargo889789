package com.example.musicapi.repository;

import com.example.musicapi.domain.Regional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionalRepository extends JpaRepository<Regional, Long> {

    Optional<Regional> findByIdExternoAndAtivoTrue(Integer idExterno);

    List<Regional> findAllByAtivoTrue();
}

