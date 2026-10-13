package br.com.fiap.spring_backend_esg.dto;

import br.com.fiap.spring_backend_esg.model.Equipamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ConsumoEnergiaCadastroDTO(
        Long id,

        @NotNull(message = "O consumo em kWh e obrigatorio!")
        @Positive(message = "O consumo em kWh deve ser positivo!")
        Double consumoKwh,

        @NotNull(message = "O equipamento do consumo e obrigatorio!")
        Equipamento equipamento
) {
}
