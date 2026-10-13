package br.com.fiap.spring_backend_esg.service;

import br.com.fiap.spring_backend_esg.dto.ConsumoEnergiaCadastroDTO;
import br.com.fiap.spring_backend_esg.dto.ConsumoEnergiaExibicaoDTO;
import br.com.fiap.spring_backend_esg.exception.RecursoNaoEncontradoException;
import br.com.fiap.spring_backend_esg.model.ConsumoEnergia;
import br.com.fiap.spring_backend_esg.model.Equipamento;
import br.com.fiap.spring_backend_esg.repository.ConsumoEnergiaRepository;
import br.com.fiap.spring_backend_esg.repository.EquipamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumoEnergiaService {
    @Autowired
    private ConsumoEnergiaRepository consumoEnergiaRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    public ConsumoEnergiaExibicaoDTO salvar(ConsumoEnergiaCadastroDTO consumoEnergiaCadastroDTO) {
        ConsumoEnergia consumoEnergia = new ConsumoEnergia();
        BeanUtils.copyProperties(consumoEnergiaCadastroDTO, consumoEnergia);
        consumoEnergia.setEquipamento(prepararEquipamento(consumoEnergiaCadastroDTO.equipamento()));
        return new ConsumoEnergiaExibicaoDTO(consumoEnergiaRepository.save(consumoEnergia));
    }

    public ConsumoEnergiaExibicaoDTO buscarPorId(Long id) {
        Optional<ConsumoEnergia> consumoEnergiaOptional = consumoEnergiaRepository.findById(id);
        if (consumoEnergiaOptional.isPresent()) {
            return new ConsumoEnergiaExibicaoDTO(consumoEnergiaOptional.get());
        } else {
            throw new RecursoNaoEncontradoException("Consumo de energia nao encontrado no banco de dados!");
        }
    }

    public List<ConsumoEnergiaExibicaoDTO> buscarTodosConsumosEnergia() {
        return consumoEnergiaRepository.findAll()
                .stream()
                .map(ConsumoEnergiaExibicaoDTO::new)
                .toList();
    }

    public void deletarConsumoEnergia(Long id) {
        Optional<ConsumoEnergia> consumoEnergiaRemove = consumoEnergiaRepository.findById(id);
        if (consumoEnergiaRemove.isPresent()) {
            consumoEnergiaRepository.delete(consumoEnergiaRemove.get());
        } else {
            throw new RecursoNaoEncontradoException("Consumo de energia nao encontrado no banco de dados!");
        }
    }

    public ConsumoEnergia atualizar(ConsumoEnergia consumoEnergia) {
        Optional<ConsumoEnergia> consumoEnergiaAtualizar = consumoEnergiaRepository.findById(consumoEnergia.getId());
        if (consumoEnergiaAtualizar.isPresent()) {
            consumoEnergia.setEquipamento(prepararEquipamento(consumoEnergia.getEquipamento()));
            return consumoEnergiaRepository.save(consumoEnergia);
        } else {
            throw new RecursoNaoEncontradoException("Consumo de energia nao encontrado no banco de dados!");
        }
    }

    public List<ConsumoEnergiaExibicaoDTO> buscarConsumosPorEquipamento(Long equipamentoId) {
        return consumoEnergiaRepository.findByEquipamentoId(equipamentoId)
                .stream()
                .map(ConsumoEnergiaExibicaoDTO::new)
                .toList();
    }

    public Double somarConsumoPorSetor(Long setorId, LocalDateTime inicio, LocalDateTime fim) {
        Double total = consumoEnergiaRepository.somarConsumoPorSetor(setorId, inicio, fim);
        return total == null ? 0.0 : total;
    }

    private Equipamento prepararEquipamento(Equipamento equipamento) {
        if (equipamento == null) {
            return null;
        }

        if (equipamento.getId() != null) {
            return equipamentoRepository.findById(equipamento.getId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Equipamento nao encontrado no banco de dados!"));
        }

        return equipamentoRepository.save(equipamento);
    }
}
