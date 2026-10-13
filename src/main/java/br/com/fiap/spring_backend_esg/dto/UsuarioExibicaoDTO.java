package br.com.fiap.spring_backend_esg.dto;


import br.com.fiap.spring_backend_esg.model.Empresa;
import br.com.fiap.spring_backend_esg.model.Usuario;
import br.com.fiap.spring_backend_esg.model.UsuarioRole;

public record UsuarioExibicaoDTO(
        Long id,
        String nome,
        String email,
        UsuarioRole role,
        Empresa empresa
) {
    public UsuarioExibicaoDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole(),
                usuario.getEmpresa()
        );
    }
}
