package com.example.Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Application.domain.Fisico;

@Repository
public interface FisicoRepository extends JpaRepository<Fisico, String>{
    
}
