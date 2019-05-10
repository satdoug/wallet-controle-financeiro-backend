package com.wallet.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pessoa_id")
    private Integer id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "dataAniversario")
    private Date dataAniversario;

    @Column(name = "Salario")
    private Double salario;

    @Column(name = "DataInsercao")
    private LocalDate dataInsercao;

    @Column(name = "DataAtualizacao")
    private LocalDate dataAtualizacao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Carteira> carteiras;


    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    @JsonManagedReference
    private Usuario usuario;

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
