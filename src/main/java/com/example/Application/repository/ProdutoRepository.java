package com.example.Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Application.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String>{
    
}
