package br.com.fiap.spring_backend_esg.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotBlank(message = "O email do usuário é obrigatório!")
        @Email(message = "O email não é válido!") //verifica somente se há @ | .com não é verificado
        String email,

        @NotBlank(message = "A senha do usuário é obrigatório!")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres")
        String senha
) {
}
