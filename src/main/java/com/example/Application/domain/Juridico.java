package com.example.Application.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "juridico")
public class Juridico extends Pessoa{
        
    @Column(name = "cnpj")
    private String cnpj;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "juridico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos = new ArrayList<>();

    public Juridico(){

    }

    public Juridico(String cnpj){
        this.cnpj = cnpj;
    }

    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }

    public String getCnpj(){
        return this.cnpj;
    }
}
