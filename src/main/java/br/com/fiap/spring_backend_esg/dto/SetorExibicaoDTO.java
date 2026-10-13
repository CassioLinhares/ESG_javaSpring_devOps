package br.com.fiap.spring_backend_esg.dto;

import br.com.fiap.spring_backend_esg.model.Empresa;
import br.com.fiap.spring_backend_esg.model.Setor;

public record SetorExibicaoDTO(
        Long id,
        String nome,
        Empresa empresa
) {
    public SetorExibicaoDTO(Setor setor) {
        this(
                setor.getId(),
                setor.getNome(),
                setor.getEmpresa()
        );
    }
}
