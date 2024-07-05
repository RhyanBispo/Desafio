package com.example.Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Application.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, String>{
    
}
