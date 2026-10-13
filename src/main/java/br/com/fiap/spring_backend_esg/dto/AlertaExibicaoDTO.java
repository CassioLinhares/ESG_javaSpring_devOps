package br.com.fiap.spring_backend_esg.dto;

import br.com.fiap.spring_backend_esg.model.Alerta;
import br.com.fiap.spring_backend_esg.model.Setor;

import java.time.LocalDateTime;

public record AlertaExibicaoDTO(
        Long id,
        String mensagem,
        LocalDateTime dataHora,
        Setor setor
) {
    public AlertaExibicaoDTO(Alerta alerta) {
        this(
                alerta.getId(),
                alerta.getMensagem(),
                alerta.getDataHora(),
                alerta.getSetor()
        );
    }
}
