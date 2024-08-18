package org.consulta.service.spec;

import java.util.List;

import org.consulta.domain.Usuario;

public interface IUsuarioService {

    Usuario buscarPorId(Long id);

    Usuario buscarPorLogin(String login);

    Usuario buscarPorDocumento(String documento);

    List<Usuario> buscarTodos();

    void salvar(Usuario usuario);

    void atualizar(Usuario usuario);

    void excluir(Long id);
}
