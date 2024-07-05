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
import org.springframework.web.bind.annotation.RestController;

import com.example.Application.domain.Venda;
import com.example.Application.repository.VendaRepository;

import jakarta.validation.Valid;

@RestController
public class VendaController {
    
    private final VendaRepository vendaRepository; 

    public VendaController(VendaRepository vendaRepository){
        this.vendaRepository = vendaRepository;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Venda create(@RequestBody @Valid Venda venda){
        return vendaRepository.save(venda);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public List<Venda> findAll(){
        return vendaRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Venda findById(@PathVariable String Id){
        if(vendaRepository.findById(Id).isPresent()){
            Venda data = vendaRepository.findById(Id).get();
            return data;
        }

        throw new RuntimeException("nenhum dado existente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> putFisico(@PathVariable String id, @RequestBody Venda data){
        return ResponseEntity.status(HttpStatus.OK).body(
            vendaRepository.findById(id).map(recordFound -> {
                recordFound.setQuantidade(data.getQuantidade());
                recordFound.setTotal(data.getTotal());
                vendaRepository.save(recordFound);
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
