package br.com.fiap.spring_backend_esg.repository;

import br.com.fiap.spring_backend_esg.dto.SetorExibicaoDTO;
import br.com.fiap.spring_backend_esg.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SetorRepository extends JpaRepository<Setor, Long> {
    @Query("SELECT new br.com.fiap.spring_backend_esg.dto.SetorExibicaoDTO(s) FROM Setor s WHERE s.nome LIKE CONCAT('%', :nome ,'%')")
    List<SetorExibicaoDTO> buscarPorNomeDeSetor(@Param("nome") String nome);
}
