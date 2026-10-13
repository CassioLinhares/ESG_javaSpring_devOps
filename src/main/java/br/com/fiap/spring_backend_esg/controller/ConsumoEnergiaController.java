package br.com.fiap.spring_backend_esg.controller;

import br.com.fiap.spring_backend_esg.dto.ConsumoEnergiaCadastroDTO;
import br.com.fiap.spring_backend_esg.dto.ConsumoEnergiaExibicaoDTO;
import br.com.fiap.spring_backend_esg.model.ConsumoEnergia;
import br.com.fiap.spring_backend_esg.service.ConsumoEnergiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ConsumoEnergiaController {

    @Autowired
    private ConsumoEnergiaService consumoEnergiaService;

    @PostMapping("/consumos-energia")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumoEnergiaExibicaoDTO salvarConsumoEnergia(@Valid @RequestBody ConsumoEnergiaCadastroDTO consumoEnergiaCadastroDTO) {
        return consumoEnergiaService.salvar(consumoEnergiaCadastroDTO);
    }

    @GetMapping("/consumos-energia")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsumoEnergiaExibicaoDTO> buscarTodosConsumosEnergia() {
        return consumoEnergiaService.buscarTodosConsumosEnergia();
    }

    @GetMapping("/consumos-energia/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ConsumoEnergiaExibicaoDTO buscarConsumoEnergiaPorId(@PathVariable Long id) {
        return consumoEnergiaService.buscarPorId(id);
    }

    @GetMapping(value = "/consumos-energia/", params = "equipamentoId")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsumoEnergiaExibicaoDTO> buscarConsumosPorEquipamento(@RequestParam Long equipamentoId) {
        return consumoEnergiaService.buscarConsumosPorEquipamento(equipamentoId);
    }

    @GetMapping(value = "/consumos-energia/soma", params = {"setorId", "inicio", "fim"})
    @ResponseStatus(HttpStatus.OK)
    public Double somarConsumoPorSetor(
            @RequestParam Long setorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim
    ) {
        return consumoEnergiaService.somarConsumoPorSetor(setorId, inicio, fim);
    }

    @DeleteMapping("/consumos-energia/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarConsumoEnergia(@PathVariable Long id) {
        consumoEnergiaService.deletarConsumoEnergia(id);
    }

    @PutMapping("/consumos-energia")
    @ResponseStatus(HttpStatus.OK)
    public ConsumoEnergia atualizarConsumoEnergia(@RequestBody ConsumoEnergia consumoEnergia) {
        return consumoEnergiaService.atualizar(consumoEnergia);
    }
}
