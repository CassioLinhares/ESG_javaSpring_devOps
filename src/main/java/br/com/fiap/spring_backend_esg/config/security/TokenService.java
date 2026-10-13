package br.com.fiap.spring_backend_esg.config.security;

import br.com.fiap.spring_backend_esg.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${minha.palavra.secreta}")
    private String palavraChave;

    public String tokenCreated(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(palavraChave);
            String token = JWT.create()
                    .withIssuer("calorias") //nome do projeto ou entidade
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(getExpiresAt()) //data de expiração
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Não foi possível gerar o token!");
        }
    }

    public String verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraChave);
            return JWT.require(algorithm)
                    .withIssuer("calorias")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) { //return "";
            throw new RuntimeException("token inválido ou expirado!");
        }
    }

    private Instant getExpiresAt(){ //pega a hora atual e add 2h e diminui 3h p/ converter o fuso horário
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
