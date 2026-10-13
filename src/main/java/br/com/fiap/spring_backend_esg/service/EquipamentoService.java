package br.com.fiap.spring_backend_esg.service;

import br.com.fiap.spring_backend_esg.dto.EquipamentoCadastroDTO;
import br.com.fiap.spring_backend_esg.dto.EquipamentoExibicaoDTO;
import br.com.fiap.spring_backend_esg.exception.RecursoNaoEncontradoException;
import br.com.fiap.spring_backend_esg.model.Equipamento;
import br.com.fiap.spring_backend_esg.model.Setor;
import br.com.fiap.spring_backend_esg.repository.EquipamentoRepository;
import br.com.fiap.spring_backend_esg.repository.SetorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {
    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private SetorRepository setorRepository;

    public EquipamentoExibicaoDTO salvar(EquipamentoCadastroDTO equipamentoCadastroDTO) {
        Equipamento equipamento = new Equipamento();
        BeanUtils.copyProperties(equipamentoCadastroDTO, equipamento);
        equipamento.setSetor(prepararSetor(equipamentoCadastroDTO.setor()));
        return new EquipamentoExibicaoDTO(equipamentoRepository.save(equipamento));
    }

    public EquipamentoExibicaoDTO buscarPorId(Long id) {
        Optional<Equipamento> equipamentoOptional = equipamentoRepository.findById(id);
        if (equipamentoOptional.isPresent()) {
            return new EquipamentoExibicaoDTO(equipamentoOptional.get());
        } else {
            throw new RecursoNaoEncontradoException("Equipamento nao encontrado no banco de dados!");
        }
    }

    public List<EquipamentoExibicaoDTO> buscarTodosEquipamentos() {
        return equipamentoRepository.findAll()
                .stream()
                .map(EquipamentoExibicaoDTO::new)
                .toList();
    }

    public void deletarEquipamento(Long id) {
        Optional<Equipamento> equipamentoRemove = equipamentoRepository.findById(id);
        if (equipamentoRemove.isPresent()) {
            equipamentoRepository.delete(equipamentoRemove.get());
        } else {
            throw new RecursoNaoEncontradoException("Equipamento nao encontrado no banco de dados!");
        }
    }

    public Equipamento atualizar(Equipamento equipamento) {
        Optional<Equipamento> equipamentoAtualizar = equipamentoRepository.findById(equipamento.getId());
        if (equipamentoAtualizar.isPresent()) {
            equipamento.setSetor(prepararSetor(equipamento.getSetor()));
            return equipamentoRepository.save(equipamento);
        } else {
            throw new RecursoNaoEncontradoException("Equipamento nao encontrado no banco de dados!");
        }
    }

    public List<EquipamentoExibicaoDTO> buscarEquipamentosPorSetor(Long setorId) {
        return equipamentoRepository.findBySetorId(setorId)
                .stream()
                .map(EquipamentoExibicaoDTO::new)
                .toList();
    }

    public List<EquipamentoExibicaoDTO> buscarEquipamentoPorNome(String nome) {
        return equipamentoRepository.buscarPorNomeDeEquipamento(nome);
    }

    private Setor prepararSetor(Setor setor) {
        if (setor == null) {
            return null;
        }

        if (setor.getId() != null) {
            return setorRepository.findById(setor.getId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Setor nao encontrado no banco de dados!"));
        }

        return setorRepository.save(setor);
    }
}
