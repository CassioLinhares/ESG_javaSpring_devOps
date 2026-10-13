package br.com.fiap.spring_backend_esg.service;

import br.com.fiap.spring_backend_esg.dto.EmpresaCadastroDTO;
import br.com.fiap.spring_backend_esg.dto.EmpresaExibicaoDTO;
import br.com.fiap.spring_backend_esg.exception.RecursoNaoEncontradoException;
import br.com.fiap.spring_backend_esg.model.Empresa;
import br.com.fiap.spring_backend_esg.repository.EmpresaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    public EmpresaExibicaoDTO salvar(EmpresaCadastroDTO empresaCadastroDTO) {
        Empresa empresa = new Empresa();
        BeanUtils.copyProperties(empresaCadastroDTO, empresa);
        return new EmpresaExibicaoDTO(empresaRepository.save(empresa));
    }

    public EmpresaExibicaoDTO buscarPorId(Long id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            return new EmpresaExibicaoDTO(empresaOptional.get());
        } else {
            throw new RecursoNaoEncontradoException("Empresa nao encontrada no banco de dados!");
        }
    }

    public List<EmpresaExibicaoDTO> buscarTodasEmpresas() {
        return empresaRepository.findAll()
                .stream()
                .map(EmpresaExibicaoDTO::new)
                .toList();
    }

    public void deletarEmpresa(Long id) {
        Optional<Empresa> empresaRemove = empresaRepository.findById(id);
        if (empresaRemove.isPresent()) {
            empresaRepository.delete(empresaRemove.get());
        } else {
            throw new RecursoNaoEncontradoException("Empresa nao encontrada no banco de dados!");
        }
    }

    public Empresa atualizar(Empresa empresa) {
        Optional<Empresa> empresaAtualizar = empresaRepository.findById(empresa.getId());
        if (empresaAtualizar.isPresent()) {
            return empresaRepository.save(empresa);
        } else {
            throw new RecursoNaoEncontradoException("Empresa nao encontrada no banco de dados!");
        }
    }

    public EmpresaExibicaoDTO buscarEmpresaPorNome(String name) {
        Optional<Empresa> empresaOptional = empresaRepository.findByName(name);
        if (empresaOptional.isPresent()) {
            return new EmpresaExibicaoDTO(empresaOptional.get());
        } else {
            throw new RecursoNaoEncontradoException("Empresa nao encontrada no banco de dados!");
        }
    }
}
