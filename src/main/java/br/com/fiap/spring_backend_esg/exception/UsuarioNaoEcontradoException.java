package br.com.fiap.spring_backend_esg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioNaoEcontradoException extends RuntimeException {
    public UsuarioNaoEcontradoException(String message) {
        super(message);
    }
}
