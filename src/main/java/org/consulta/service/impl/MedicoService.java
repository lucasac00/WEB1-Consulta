package org.consulta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.consulta.dao.IMedicoDAO;
import org.consulta.domain.Medico;
import org.consulta.service.spec.IMedicoService;

@Service
@Transactional(readOnly = false)
public class MedicoService implements IMedicoService {

    @Autowired
    IMedicoDAO dao;

    public void salvar(Medico medico) {
        dao.save(medico);
    }

    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Medico buscarPorId(Long id) {
        return dao.findById(id.longValue());
    }

    @Transactional(readOnly = true)
    public List<Medico> buscarTodos() {
        return dao.findAll();
    }
}