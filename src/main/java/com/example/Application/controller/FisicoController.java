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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Application.domain.Fisico;
import com.example.Application.repository.FisicoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("V1/application/fisico/")
public class FisicoController {

    private final FisicoRepository fisicoRepository; 

    public  FisicoController(FisicoRepository fisicoRepository){
        this.fisicoRepository = fisicoRepository;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Fisico create(@RequestBody @Valid Fisico fisico){
        return fisicoRepository.save(fisico);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public List<Fisico> findAll(){
        return fisicoRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Fisico findById(@PathVariable String Id){
        if(fisicoRepository.findById(Id).isPresent()){
            Fisico data = fisicoRepository.findById(Id).get();
            return data;
        }

        throw new RuntimeException("nenhum dado existente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fisico> putFisico(@PathVariable String id, @RequestBody Fisico data){
        return ResponseEntity.status(HttpStatus.OK).body(
            fisicoRepository.findById(id).map(recordFound -> {
                recordFound.setCpf(data.getCpf());
                recordFound.setDataNascimento(data.getDataNascimento());
                recordFound.setNome(data.getNome());
                fisicoRepository.save(recordFound);
                return recordFound;
            }).orElseThrow(() -> new RuntimeException("Dado não encontrado")));
}

    @DeleteMapping
    public void deleteFisico(@PathVariable String id){

        Optional<Fisico> data = fisicoRepository.findById(id);

        if(data.isPresent()){
            fisicoRepository.delete(data.get());
        }else{
            throw new RuntimeException("Dado não encontrado.");
        }
        
    }
}
