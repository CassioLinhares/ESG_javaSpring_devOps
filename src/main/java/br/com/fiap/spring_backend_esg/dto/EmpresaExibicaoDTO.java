package br.com.fiap.spring_backend_esg.dto;

import br.com.fiap.spring_backend_esg.model.Empresa;

public record EmpresaExibicaoDTO(
        Long id,
        String name,
        String cnpj
) {
    public EmpresaExibicaoDTO(Empresa empresa) {
        this(
                empresa.getId(),
                empresa.getName(),
                empresa.getCnpj()
        );
    }
}
