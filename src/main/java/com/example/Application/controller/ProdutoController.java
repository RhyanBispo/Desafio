package com.example.Application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.Application.domain.Produto;
import com.example.Application.domain.Venda;
import com.example.Application.repository.ProdutoRepository;
import com.example.Application.repository.VendaRepository;

import jakarta.validation.Valid;

public class ProdutoController {
    
    
    private final ProdutoRepository produtoRepository; 

    public ProdutoController(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Produto create(@RequestBody @Valid Produto venda){
        return produtoRepository.save(venda);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Produto findById(@PathVariable String Id){
        if(produtoRepository.findById(Id).isPresent()){
            Produto data = produtoRepository.findById(Id).get();
            return data;
        }

        throw new RuntimeException("nenhum dado existente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> putFisico(@PathVariable String id, @RequestBody Produto data){
        return ResponseEntity.status(HttpStatus.OK).body(
            produtoRepository.findById(id).map(recordFound -> {
                recordFound.setNome(data.getNome());
                recordFound.setValor(data.getValor());
                produtoRepository.save(recordFound);
                return recordFound;
            }).orElseThrow(() -> new RuntimeException("Dado não encontrado")));
}

    @DeleteMapping
    public void deleteFisico(@PathVariable String id){

        Optional<Venda> data = vendaRepository.findById(id);

        if(data.isPresent()){
            vendaRepository.delete(data.get());
        }else{
            throw new RuntimeException("Dado não encontrado.");
        }
        
    }
}
