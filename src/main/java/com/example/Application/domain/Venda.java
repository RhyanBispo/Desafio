package com.example.Application.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name ="venda")
public class Venda {
    
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;

    @Column(name = "total")
    private Double total;

    @Column(name = "quantidade")
    private Integer quantidade;

    @ManyToOne
    private Fisico fisico;

    @ManyToOne
    private Produto produto;

    public Venda(){
    }

    public Venda(String Id, Double total, Integer quantidade){
        this.Id = Id;
        this.quantidade = quantidade;
        this.total = total;
    }

    public void setTotal(Double total){
        this.total = total;
    }
    public Double getTotal(){
        return this.total;
    }

    public void setQuantidade(Integer quantidade){
        this.quantidade = quantidade;
    }
    public Integer getQuantidade(){
        return this.quantidade;
    }
}
