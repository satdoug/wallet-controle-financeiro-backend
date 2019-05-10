package com.wallet.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.wallet.domain.enuns.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Data
@NoArgsConstructor
@Entity
public class Transacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transacao_id")
    private Integer id;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "DataTransacao")
    private LocalDate dataDaTransacao;

    @Column(name = "ValorTransacao")
    private BigDecimal valor;

    @Column(name = "DataInsercao")
    private LocalDate dataInsercao;

    @Column(name = "DataAtualizacao")
    private LocalDate dataAtualizacao;

    @Column(name = "tipoTransacao")
    private TipoTransacao TipoTrasacao;

    @Column(name = "DataVencimento")
    private LocalDate dataVencimento;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "carteira_id")
    @JsonBackReference
    private Carteira carteira;

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = LocalDate.now();
    }

    @PrePersist
    public void prePersist() {
        final LocalDate atual =  LocalDate.now();
        dataInsercao = atual;
        dataAtualizacao = atual;
    }

    public Transacao(String descricao, LocalDate dataDaTransacao, BigDecimal valor, LocalDate dataAtualizacao, TipoTransacao tipoTrasacao, LocalDate dataVencimento) {
        this.descricao = descricao;
        this.dataDaTransacao = dataDaTransacao;
        this.valor = valor;
        this.dataAtualizacao = dataAtualizacao;
        TipoTrasacao = tipoTrasacao;
        this.dataVencimento = dataVencimento;
    }
}
