package com.example.Application.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "endereco")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Endereco {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    
    @Column(name="cep")
    private String cep;

    @Column(name="cidade")
    private String cidade;

    @Column(name="bairro")
    private String bairro;

    @Column(name="rua")
    private String rua;

    @Column(name="numero")
    private String numero;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Fisico fisico;

}
