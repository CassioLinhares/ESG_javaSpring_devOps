package br.com.fiap.spring_backend_esg.controller;

import br.com.fiap.spring_backend_esg.dto.UsuarioCadastroDTO;
import br.com.fiap.spring_backend_esg.dto.UsuarioExibicaoDTO;
import br.com.fiap.spring_backend_esg.model.Usuario;
import br.com.fiap.spring_backend_esg.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO salvarUsuario(@Valid @RequestBody UsuarioCadastroDTO usuarioCadastroDTO){
        return usuarioService.salvar(usuarioCadastroDTO);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioExibicaoDTO> buscarTodosUsuarios(){
        return usuarioService.buscarTodosUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDTO buscarUsuarioPorId(@PathVariable Long id){
        return usuarioService.buscarPorId(id);
    }

    @GetMapping(value = "/usuarios/", params = "nome")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioExibicaoDTO> buscarUsuarioPorNome(@RequestParam String nome){
        return usuarioService.buscarUsuarioPorNome(nome);
    }

    @DeleteMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDTO atualizarUsuario(@RequestBody Usuario usuario){
        return new UsuarioExibicaoDTO(usuarioService.atualizar(usuario));
    }

}
