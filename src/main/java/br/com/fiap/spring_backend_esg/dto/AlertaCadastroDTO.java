package br.com.fiap.spring_backend_esg.dto;

import br.com.fiap.spring_backend_esg.model.Setor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlertaCadastroDTO(
        Long id,

        @NotBlank(message = "A mensagem do alerta e obrigatoria!")
        String mensagem,

        @NotNull(message = "O setor do alerta e obrigatorio!")
        Setor setor
) {
}
