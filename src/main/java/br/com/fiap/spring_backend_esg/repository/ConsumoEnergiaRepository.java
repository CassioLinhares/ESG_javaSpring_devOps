package br.com.fiap.spring_backend_esg.repository;

import br.com.fiap.spring_backend_esg.model.ConsumoEnergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsumoEnergiaRepository extends JpaRepository<ConsumoEnergia, Long> {
    List<ConsumoEnergia> findByEquipamentoId(Long id);

    @Query("""
        SELECT SUM(c.consumoKwh)
        FROM ConsumoEnergia c
        WHERE c.equipamento.setor.id = :setorId
        AND c.dataHora BETWEEN :inicio AND :fim
    """)
    Double somarConsumoPorSetor(
            @Param("setorId") Long setorId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );
}
