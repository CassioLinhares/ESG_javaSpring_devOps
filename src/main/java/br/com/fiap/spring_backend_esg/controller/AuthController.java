package br.com.fiap.spring_backend_esg.controller;

import br.com.fiap.spring_backend_esg.config.security.TokenService;
import br.com.fiap.spring_backend_esg.dto.LoginDTO;
import br.com.fiap.spring_backend_esg.dto.TokenDTO;
import br.com.fiap.spring_backend_esg.dto.UsuarioCadastroDTO;
import br.com.fiap.spring_backend_esg.dto.UsuarioExibicaoDTO;
import br.com.fiap.spring_backend_esg.model.Usuario;
import br.com.fiap.spring_backend_esg.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
//@RequiredArgsConstructor //injeção de dependencias do Lombok envés do @Autowired | melhor pra tests
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO usuarioLoginDTO){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(usuarioLoginDTO.email(), usuarioLoginDTO.senha());
        Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = tokenService.tokenCreated( (Usuario) auth.getPrincipal() );
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UsuarioCadastroDTO usuarioCadastroDTO){
        UsuarioExibicaoDTO usuarioSalvo = usuarioService.salvar(usuarioCadastroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }
}
