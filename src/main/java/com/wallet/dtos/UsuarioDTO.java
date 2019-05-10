package com.wallet.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class UsuarioDTO {

    @NotEmpty(message = "Preenchimento obrigatórios")
    @Length(min = 5, max = 10, message = "O tamanho deve ser entre 5 e 10 caracteres")
    private String login;

    @NotEmpty(message = "Preenchimento obrigatórios")
    private String senha;

}
