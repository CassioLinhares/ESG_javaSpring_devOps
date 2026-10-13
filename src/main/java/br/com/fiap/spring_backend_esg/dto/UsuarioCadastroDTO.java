package br.com.fiap.spring_backend_esg.dto;


import br.com.fiap.spring_backend_esg.model.Empresa;
import br.com.fiap.spring_backend_esg.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDTO(
        Long id,

        @NotBlank(message = "O nome do usuário é obrigatório!")
        String nome,

        @NotBlank(message = "O email do usuário é obrigatório!")
        @Email(message = "O email não é válido!") //verifica somente se há @ | .com não é verificado
        String email,

        @NotBlank(message = "A senha do usuário é obrigatório!")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres")
        String senha,

        UsuarioRole role,
        Empresa empresa
) {
}
