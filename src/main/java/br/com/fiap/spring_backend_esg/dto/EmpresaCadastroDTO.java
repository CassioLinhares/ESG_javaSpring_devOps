package br.com.fiap.spring_backend_esg.dto;

import jakarta.validation.constraints.NotBlank;

public record EmpresaCadastroDTO(
        Long id,

        @NotBlank(message = "O nome da empresa e obrigatório!")
        String name,

        @NotBlank(message = "O CNPJ da empresa e obrigatório!")
        String cnpj
) {
}
