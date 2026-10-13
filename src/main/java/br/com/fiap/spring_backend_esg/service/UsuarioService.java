package br.com.fiap.spring_backend_esg.service;

import br.com.fiap.spring_backend_esg.dto.UsuarioCadastroDTO;
import br.com.fiap.spring_backend_esg.dto.UsuarioExibicaoDTO;
import br.com.fiap.spring_backend_esg.exception.RecursoNaoEncontradoException;
import br.com.fiap.spring_backend_esg.exception.UsuarioNaoEcontradoException;
import br.com.fiap.spring_backend_esg.model.Empresa;
import br.com.fiap.spring_backend_esg.model.Usuario;
import br.com.fiap.spring_backend_esg.repository.EmpresaRepository;
import br.com.fiap.spring_backend_esg.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public UsuarioExibicaoDTO salvar(UsuarioCadastroDTO usuarioCadastroDTO) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDTO.senha());
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDTO, usuario);
        usuario.setEmpresa(prepararEmpresa(usuarioCadastroDTO.empresa()));
        usuario.setSenha(senhaCriptografada);
        return new UsuarioExibicaoDTO(usuarioRepository.save(usuario));
    }

    public UsuarioExibicaoDTO buscarPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDTO(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEcontradoException("Usuario nao encontrado no banco de dados!");
        }
    }

    public List<UsuarioExibicaoDTO> buscarTodosUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioExibicaoDTO::new)
                .toList();
    }

    public void deletarUsuario(Long id) {
        Optional<Usuario> usuarioRemove = usuarioRepository.findById(id);
        if (usuarioRemove.isPresent()) {
            usuarioRepository.delete(usuarioRemove.get());
        } else {
            throw new UsuarioNaoEcontradoException("Usuario nao encontrado no banco de dados!");
        }
    }

    public Usuario atualizar(Usuario usuario) {
        Optional<Usuario> usuarioAtualizar = usuarioRepository.findById(usuario.getId());
        if (usuarioAtualizar.isPresent()) {
            usuario.setEmpresa(prepararEmpresa(usuario.getEmpresa()));
            return usuarioRepository.save(usuario);
        } else {
            throw new UsuarioNaoEcontradoException("Usuário nao encontrado no banco de dados!");
        }
    }

    public List<UsuarioExibicaoDTO> buscarUsuarioPorNome(String nome) {
        return usuarioRepository.buscarPorUsuarioPorNome(nome);
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
