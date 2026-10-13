package br.com.fiap.spring_backend_esg.repository;

import br.com.fiap.spring_backend_esg.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findBySetorId(Long id);
}
