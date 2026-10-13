package br.com.fiap.spring_backend_esg.repository;

import br.com.fiap.spring_backend_esg.dto.EquipamentoExibicaoDTO;
import br.com.fiap.spring_backend_esg.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
        List<Equipamento> findBySetorId(Long id);

        @Query("SELECT new br.com.fiap.spring_backend_esg.dto.EquipamentoExibicaoDTO(e) FROM Equipamento e WHERE e.nome LIKE CONCAT('%', :nome ,'%')")
        List<EquipamentoExibicaoDTO> buscarPorNomeDeEquipamento(@Param("nome") String nome);
}
