package br.com.fiap.spring_backend_esg.service;

import br.com.fiap.spring_backend_esg.dto.AlertaCadastroDTO;
import br.com.fiap.spring_backend_esg.dto.AlertaExibicaoDTO;
import br.com.fiap.spring_backend_esg.exception.RecursoNaoEncontradoException;
import br.com.fiap.spring_backend_esg.model.Alerta;
import br.com.fiap.spring_backend_esg.model.Setor;
import br.com.fiap.spring_backend_esg.repository.AlertaRepository;
import br.com.fiap.spring_backend_esg.repository.SetorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertaService {
    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private SetorRepository setorRepository;

    public AlertaExibicaoDTO salvar(AlertaCadastroDTO alertaCadastroDTO) {
        Alerta alerta = new Alerta();
        BeanUtils.copyProperties(alertaCadastroDTO, alerta);
        alerta.setSetor(prepararSetor(alertaCadastroDTO.setor()));
        return new AlertaExibicaoDTO(alertaRepository.save(alerta));
    }

    public AlertaExibicaoDTO buscarPorId(Long id) {
        Optional<Alerta> alertaOptional = alertaRepository.findById(id);
        if (alertaOptional.isPresent()) {
            return new AlertaExibicaoDTO(alertaOptional.get());
        } else {
            throw new RecursoNaoEncontradoException("Alerta nao encontrado no banco de dados!");
        }
    }

    public List<AlertaExibicaoDTO> buscarTodosAlertas() {
        return alertaRepository.findAll()
                .stream()
                .map(AlertaExibicaoDTO::new)
                .toList();
    }

    public void deletarAlerta(Long id) {
        Optional<Alerta> alertaRemove = alertaRepository.findById(id);
        if (alertaRemove.isPresent()) {
            alertaRepository.delete(alertaRemove.get());
        } else {
            throw new RecursoNaoEncontradoException("Alerta nao encontrado no banco de dados!");
        }
    }

    public Alerta atualizar(Alerta alerta) {
        Optional<Alerta> alertaAtualizar = alertaRepository.findById(alerta.getId());
        if (alertaAtualizar.isPresent()) {
            alerta.setSetor(prepararSetor(alerta.getSetor()));
            return alertaRepository.save(alerta);
        } else {
            throw new RecursoNaoEncontradoException("Alerta nao encontrado no banco de dados!");
        }
    }

    public List<AlertaExibicaoDTO> buscarAlertasPorSetor(Long setorId) {
        return alertaRepository.findBySetorId(setorId)
                .stream()
                .map(AlertaExibicaoDTO::new)
                .toList();
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
