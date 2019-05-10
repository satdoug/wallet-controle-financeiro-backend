package com.wallet.dtos;

import com.wallet.domain.Carteira;
import com.wallet.domain.Transacao;
import com.wallet.domain.enuns.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Data
@NoArgsConstructor
public class TransacaoDTO {

    @NotEmpty(message = "Preenchimento obrigatórios")
    @Length(min = 5, max = 20, message = "O tamanho deve ser entre 5 e 20 caracteres")
    private String descricao;

    private LocalDate dataVencimento;

    private LocalDate dataDaTransacao;

    @NotEmpty(message = "Preenchimento obrigatórios")
    private BigDecimal valor;

    @NotEmpty(message = "Preenchimento obrigatórios")
    private TipoTransacao TipoTrasacao;

    private Optional<Integer> id = Optional.empty();

    private  LocalDate dataAtualizacao;

    public Transacao transformaParaObjeto(){
        return new Transacao(descricao, dataDaTransacao, valor, dataAtualizacao, TipoTrasacao, dataVencimento);
    }
}
