package br.com.fiap.spring_backend_esg.controller;

import br.com.fiap.spring_backend_esg.dto.AlertaCadastroDTO;
import br.com.fiap.spring_backend_esg.dto.AlertaExibicaoDTO;
import br.com.fiap.spring_backend_esg.model.Alerta;
import br.com.fiap.spring_backend_esg.service.AlertaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @PostMapping("/alertas")
    @ResponseStatus(HttpStatus.CREATED)
    public AlertaExibicaoDTO salvarAlerta(@Valid @RequestBody AlertaCadastroDTO alertaCadastroDTO) {
        return alertaService.salvar(alertaCadastroDTO);
    }

    @GetMapping("/alertas")
    @ResponseStatus(HttpStatus.OK)
    public List<AlertaExibicaoDTO> buscarTodosAlertas() {
        return alertaService.buscarTodosAlertas();
    }

    @GetMapping("/alertas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlertaExibicaoDTO buscarAlertaPorId(@PathVariable Long id) {
        return alertaService.buscarPorId(id);
    }

    @GetMapping(value = "/alertas/", params = "setorId")
    @ResponseStatus(HttpStatus.OK)
    public List<AlertaExibicaoDTO> buscarAlertasPorSetor(@RequestParam Long setorId) {
        return alertaService.buscarAlertasPorSetor(setorId);
    }

    @DeleteMapping("/alertas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAlerta(@PathVariable Long id) {
        alertaService.deletarAlerta(id);
    }

    @PutMapping("/alertas")
    @ResponseStatus(HttpStatus.OK)
    public Alerta atualizarAlerta(@RequestBody Alerta alerta) {
        return alertaService.atualizar(alerta);
    }
}
