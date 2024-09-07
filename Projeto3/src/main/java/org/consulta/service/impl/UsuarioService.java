package org.consulta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.consulta.dao.IUsuarioDAO;
import org.consulta.domain.Usuario;
import org.consulta.service.spec.IUsuarioService;

@Service
@Transactional(readOnly = false)
public class UsuarioService implements IUsuarioService {

    @Autowired
    IUsuarioDAO dao;

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) { return dao.findById(id.longValue()); }

    @Transactional(readOnly = true)
    public Usuario buscarPorLogin(String login) { return dao.findByUsername(login); }

    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String login) { return dao.findByUsername(login); }

    @Transactional(readOnly = true)
    public Usuario buscarPorDocumento(String documento) {
        return dao.findByCpf(documento);
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() { return dao.findAll(); }

    public void salvar(Usuario usuario) { dao.save(usuario); }

    public void atualizar(Usuario usuario) { dao.save(usuario); }

    public void excluir(Long id) { dao.deleteById(id); }
}
