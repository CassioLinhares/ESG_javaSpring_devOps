package br.com.fiap.spring_backend_esg.controller;

import br.com.fiap.spring_backend_esg.dto.EquipamentoCadastroDTO;
import br.com.fiap.spring_backend_esg.dto.EquipamentoExibicaoDTO;
import br.com.fiap.spring_backend_esg.model.Equipamento;
import br.com.fiap.spring_backend_esg.service.EquipamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @PostMapping("/equipamentos")
    @ResponseStatus(HttpStatus.CREATED)
    public EquipamentoExibicaoDTO salvarEquipamento(@Valid @RequestBody EquipamentoCadastroDTO equipamentoCadastroDTO) {
        return equipamentoService.salvar(equipamentoCadastroDTO);
    }

    @GetMapping("/equipamentos")
    @ResponseStatus(HttpStatus.OK)
    public List<EquipamentoExibicaoDTO> buscarTodosEquipamentos() {
        return equipamentoService.buscarTodosEquipamentos();
    }

    @GetMapping("/equipamentos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EquipamentoExibicaoDTO buscarEquipamentoPorId(@PathVariable Long id) {
        return equipamentoService.buscarPorId(id);
    }

    @GetMapping(value = "/equipamentos/", params = "setorId")
    @ResponseStatus(HttpStatus.OK)
    public List<EquipamentoExibicaoDTO> buscarEquipamentosPorSetor(@RequestParam Long setorId) {
        return equipamentoService.buscarEquipamentosPorSetor(setorId);
    }

    @GetMapping(value = "/equipamentos/", params = "nome")
    @ResponseStatus(HttpStatus.OK)
    public List<EquipamentoExibicaoDTO> buscarEquipamentoPorNome(@RequestParam String nome) {
        return equipamentoService.buscarEquipamentoPorNome(nome);
    }

    @DeleteMapping("/equipamentos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEquipamento(@PathVariable Long id) {
        equipamentoService.deletarEquipamento(id);
    }

    @PutMapping("/equipamentos")
    @ResponseStatus(HttpStatus.OK)
    public Equipamento atualizarEquipamento(@RequestBody Equipamento equipamento) {
        return equipamentoService.atualizar(equipamento);
    }
}
