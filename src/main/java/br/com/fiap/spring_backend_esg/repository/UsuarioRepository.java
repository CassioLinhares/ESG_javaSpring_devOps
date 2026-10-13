package br.com.fiap.spring_backend_esg.repository;

import br.com.fiap.spring_backend_esg.dto.UsuarioExibicaoDTO;
import br.com.fiap.spring_backend_esg.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT new br.com.fiap.spring_backend_esg.dto.UsuarioExibicaoDTO(u) FROM Usuario u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome ,'%'))")
    List<UsuarioExibicaoDTO> buscarPorUsuarioPorNome(@Param("nome") String nome);

    UserDetails findByEmail(String email);
}
