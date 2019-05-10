package com.wallet.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer id;

    @Column(name = "Login", unique = true)
    private String login;

    @Column(name = "Senha")
    private String senha;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    @JsonBackReference
    private Pessoa pessoa;

    @Column(name = "DataInsercao")
    private LocalDate dataInsercao;

    @Column(name = "DataAtualizacao")
    private LocalDate dataAtualizacao;

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
}
