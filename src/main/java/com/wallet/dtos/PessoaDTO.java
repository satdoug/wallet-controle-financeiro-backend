package com.wallet.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class PessoaDTO {

    @NotEmpty(message = "Preenchimento obrigatórios")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatórios")
    private Double salario;

}
