package br.com.fiap.spring_backend_esg.repository;

import br.com.fiap.spring_backend_esg.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
        Optional<Empresa> findByName(String name);

        boolean existsByCnpj(String cnpj);
}
