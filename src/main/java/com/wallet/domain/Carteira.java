package com.wallet.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Carteira implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Carteira_id")
    private Integer id;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "DataInsercao")
    private LocalDate dataInsercao;

    @Column(name = "DataAtualizacao")
    private LocalDate dataAtualizacao;

    @OneToMany(mappedBy = "carteira", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Transacao> transacoes;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Pessoa pessoa;

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
