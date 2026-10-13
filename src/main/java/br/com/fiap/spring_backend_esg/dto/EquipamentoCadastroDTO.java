package br.com.fiap.spring_backend_esg.dto;

import br.com.fiap.spring_backend_esg.model.Setor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EquipamentoCadastroDTO(
        Long id,

        @NotBlank(message = "O nome do equipamento e obrigatório!")
        String nome,

        @NotBlank(message = "O tipo do equipamento e obrigatório!")
        String tipo,

        @NotBlank(message = "O status do equipamento e obrigatório!")
        String status,

        @NotNull(message = "O setor do equipamento e obrigatório!")
        Setor setor
) {
}
