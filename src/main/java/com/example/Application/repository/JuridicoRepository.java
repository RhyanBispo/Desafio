package com.example.Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Application.domain.Juridico;

public interface JuridicoRepository extends JpaRepository<Juridico, String>{
    
}
