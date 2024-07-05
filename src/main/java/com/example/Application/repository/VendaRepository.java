package com.example.Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Application.domain.Venda;

public interface VendaRepository extends JpaRepository<Venda, String>{
    
}
