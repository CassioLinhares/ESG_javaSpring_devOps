package br.com.fiap.spring_backend_esg.dto;

import br.com.fiap.spring_backend_esg.model.ConsumoEnergia;
import br.com.fiap.spring_backend_esg.model.Equipamento;

import java.time.LocalDateTime;

public record ConsumoEnergiaExibicaoDTO(
        Long id,
        Double consumoKwh,
        LocalDateTime dataHora,
        Equipamento equipamento
) {
    public ConsumoEnergiaExibicaoDTO(ConsumoEnergia consumoEnergia) {
        this(
                consumoEnergia.getId(),
                consumoEnergia.getConsumoKwh(),
                consumoEnergia.getDataHora(),
                consumoEnergia.getEquipamento()
        );
    }
}
