package br.com.fiap.spring_backend_esg.dto;

import br.com.fiap.spring_backend_esg.model.Empresa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SetorCadastroDTO(
        Long id,

        @NotBlank(message = "O nome do setor e obrigatório!")
        String nome,

        @NotNull(message = "A empresa do setor e obrigatória!")
        Empresa empresa
) {
}
