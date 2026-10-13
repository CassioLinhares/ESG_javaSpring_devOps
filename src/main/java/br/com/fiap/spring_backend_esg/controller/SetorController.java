package br.com.fiap.spring_backend_esg.controller;

import br.com.fiap.spring_backend_esg.dto.SetorCadastroDTO;
import br.com.fiap.spring_backend_esg.dto.SetorExibicaoDTO;
import br.com.fiap.spring_backend_esg.model.Setor;
import br.com.fiap.spring_backend_esg.service.SetorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SetorController {

    @Autowired
    private SetorService setorService;

    @PostMapping("/setores")
    @ResponseStatus(HttpStatus.CREATED)
    public SetorExibicaoDTO salvarSetor(@Valid @RequestBody SetorCadastroDTO setorCadastroDTO) {
        return setorService.salvar(setorCadastroDTO);
    }

    @GetMapping("/setores")
    @ResponseStatus(HttpStatus.OK)
    public List<SetorExibicaoDTO> buscarTodosSetores() {
        return setorService.buscarTodosSetores();
    }

    @GetMapping("/setores/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SetorExibicaoDTO buscarSetorPorId(@PathVariable Long id) {
        return setorService.buscarPorId(id);
    }

    @GetMapping(value = "/setores/", params = "nome")
    @ResponseStatus(HttpStatus.OK)
    public List<SetorExibicaoDTO> buscarSetorPorNome(@RequestParam String nome) {
        return setorService.buscarSetorPorNome(nome);
    }

    @DeleteMapping("/setores/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarSetor(@PathVariable Long id) {
        setorService.deletarSetor(id);
    }

    @PutMapping("/setores")
    @ResponseStatus(HttpStatus.OK)
    public Setor atualizarSetor(@RequestBody Setor setor) {
        return setorService.atualizar(setor);
    }
}
