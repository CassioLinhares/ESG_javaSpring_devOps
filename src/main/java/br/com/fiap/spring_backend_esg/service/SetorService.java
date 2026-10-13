package br.com.fiap.spring_backend_esg.service;

import br.com.fiap.spring_backend_esg.dto.SetorCadastroDTO;
import br.com.fiap.spring_backend_esg.dto.SetorExibicaoDTO;
import br.com.fiap.spring_backend_esg.exception.RecursoNaoEncontradoException;
import br.com.fiap.spring_backend_esg.model.Empresa;
import br.com.fiap.spring_backend_esg.model.Setor;
import br.com.fiap.spring_backend_esg.repository.EmpresaRepository;
import br.com.fiap.spring_backend_esg.repository.SetorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SetorService {
    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public SetorExibicaoDTO salvar(SetorCadastroDTO setorCadastroDTO) {
        Setor setor = new Setor();
        BeanUtils.copyProperties(setorCadastroDTO, setor);
        setor.setEmpresa(prepararEmpresa(setorCadastroDTO.empresa()));
        return new SetorExibicaoDTO(setorRepository.save(setor));
    }

    public SetorExibicaoDTO buscarPorId(Long id) {
        Optional<Setor> setorOptional = setorRepository.findById(id);
        if (setorOptional.isPresent()) {
            return new SetorExibicaoDTO(setorOptional.get());
        } else {
            throw new RecursoNaoEncontradoException("Setor nao encontrado no banco de dados!");
        }
    }

    public List<SetorExibicaoDTO> buscarTodosSetores() {
        return setorRepository.findAll()
                .stream()
                .map(SetorExibicaoDTO::new)
                .toList();
    }

    public void deletarSetor(Long id) {
        Optional<Setor> setorRemove = setorRepository.findById(id);
        if (setorRemove.isPresent()) {
            setorRepository.delete(setorRemove.get());
        } else {
            throw new RecursoNaoEncontradoException("Setor nao encontrado no banco de dados!");
        }
    }

    public Setor atualizar(Setor setor) {
        Optional<Setor> setorAtualizar = setorRepository.findById(setor.getId());
        if (setorAtualizar.isPresent()) {
            setor.setEmpresa(prepararEmpresa(setor.getEmpresa()));
            return setorRepository.save(setor);
        } else {
            throw new RecursoNaoEncontradoException("Setor nao encontrado no banco de dados!");
        }
    }

    public List<SetorExibicaoDTO> buscarSetorPorNome(String nome) {
        return setorRepository.buscarPorNomeDeSetor(nome);
    }

    private Empresa prepararEmpresa(Empresa empresa) {
        if (empresa == null) {
            return null;
        }

        if (empresa.getId() != null) {
            return empresaRepository.findById(empresa.getId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Empresa nao encontrada no banco de dados!"));
        }

        return empresaRepository.save(empresa);
    }
}
