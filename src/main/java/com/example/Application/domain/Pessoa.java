package com.example.Application.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class Pessoa {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_nascimento")
    private String dataNascimento;

    public Pessoa(){

    }

    public Pessoa(String Id, String nome, String dataNascimento){
        this.Id = Id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    
    public void setDataNascimento(String dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    public String getDataNascimento(){
        return this.dataNascimento;
    }
    
    

}
