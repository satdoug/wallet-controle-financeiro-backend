package com.wallet.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class CarteiraDTO {

    @NotEmpty(message = "Preenchimento obrigatórios")
    @Length(min = 5, max = 20, message = "O tamanho deve ser entre 5 e 20 caracteres")
    private String descricao;

}
