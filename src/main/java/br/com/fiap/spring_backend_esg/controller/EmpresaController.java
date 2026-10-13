package br.com.fiap.spring_backend_esg.controller;

import br.com.fiap.spring_backend_esg.dto.EmpresaCadastroDTO;
import br.com.fiap.spring_backend_esg.dto.EmpresaExibicaoDTO;
import br.com.fiap.spring_backend_esg.model.Empresa;
import br.com.fiap.spring_backend_esg.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/empresas")
    @ResponseStatus(HttpStatus.CREATED)
    public EmpresaExibicaoDTO salvarEmpresa(@Valid @RequestBody EmpresaCadastroDTO empresaCadastroDTO) {
        return empresaService.salvar(empresaCadastroDTO);
    }

    @GetMapping("/empresas")
    @ResponseStatus(HttpStatus.OK)
    public List<EmpresaExibicaoDTO> buscarTodasEmpresas() {
        return empresaService.buscarTodasEmpresas();
    }

    @GetMapping("/empresas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmpresaExibicaoDTO buscarEmpresaPorId(@PathVariable Long id) {
        return empresaService.buscarPorId(id);
    }

    @GetMapping(value = "/empresas/", params = "name")
    @ResponseStatus(HttpStatus.OK)
    public EmpresaExibicaoDTO buscarEmpresaPorNome(@RequestParam String name) {
        return empresaService.buscarEmpresaPorNome(name);
    }

    @DeleteMapping("/empresas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEmpresa(@PathVariable Long id) {
        empresaService.deletarEmpresa(id);
    }

    @PutMapping("/empresas")
    @ResponseStatus(HttpStatus.OK)
    public Empresa atualizarEmpresa(@RequestBody Empresa empresa) {
        return empresaService.atualizar(empresa);
    }
}
