package br.com.fiap.spring_backend_esg.dto;

import br.com.fiap.spring_backend_esg.model.Equipamento;
import br.com.fiap.spring_backend_esg.model.Setor;

public record EquipamentoExibicaoDTO(
        Long id,
        String nome,
        String tipo,
        String status,
        Setor setor
) {
    public EquipamentoExibicaoDTO(Equipamento equipamento) {
        this(
                equipamento.getId(),
                equipamento.getNome(),
                equipamento.getTipo(),
                equipamento.getStatus(),
                equipamento.getSetor()
        );
    }
}
