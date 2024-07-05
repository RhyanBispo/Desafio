package com.example.Application.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name = "fisico")
public class Fisico extends Pessoa{
    
    @Column(name = "cpf")
    private String cpf;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "fisico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venda> vendas = new ArrayList<>();

    @NotEmpty(message = "Pessoa Fisica precisa ter pelo o menos um endereço")
    @OneToMany(mappedBy = "fisico", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private List<Endereco> endereco = new ArrayList<>();

    public Fisico(){

    }
    public Fisico(String cpf){
        this.cpf = cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getCpf(){
        return this.cpf;
    }

}
