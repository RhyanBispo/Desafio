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

import com.example.Application.domain.Juridico;
import com.example.Application.repository.JuridicoRepository;

import jakarta.validation.Valid;

@RestController
public class JuridicoController {
    private final JuridicoRepository juridicoRepository; 

    public  JuridicoController(JuridicoRepository juridicoRepository){
        this.juridicoRepository = juridicoRepository;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Juridico create(@RequestBody @Valid Juridico juridico){
        return juridicoRepository.save(juridico);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public List<Juridico> findAll(){
        return juridicoRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Juridico findById(@PathVariable String Id){
        if(juridicoRepository.findById(Id).isPresent()){
            Juridico data = juridicoRepository.findById(Id).get();
            return data;
        }

        throw new RuntimeException("nenhum dado existente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Juridico> putFisico(@PathVariable String id, @RequestBody Juridico data){
        return ResponseEntity.status(HttpStatus.OK).body(
            juridicoRepository.findById(id).map(recordFound -> {
                recordFound.setCnpj(data.getCnpj());
                recordFound.setDataNascimento(data.getDataNascimento());
                recordFound.setNome(data.getNome());
                juridicoRepository.save(recordFound);
                return recordFound;
            }).orElseThrow(() -> new RuntimeException("Dado não encontrado")));
}

    @DeleteMapping
    public void deleteFisico(@PathVariable String id){

        Optional<Juridico> data = juridicoRepository.findById(id);

        if(data.isPresent()){
            juridicoRepository.delete(data.get());
        }else{
            throw new RuntimeException("Dado não encontrado.");
        }
        
    }
}
