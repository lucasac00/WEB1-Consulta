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

    public void atualizar(Medico medico) { dao.save(medico); }

    @Transactional(readOnly = true)
    public Medico buscarPorId(long id) {
        return dao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Medico> buscarTodos() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Medico> buscarPorEspecialidade(String especialidade) {
        return dao.findByEspecialidade(especialidade);
    }

    @Transactional(readOnly = true)
    public Medico buscarPorCrm(String crm) { return dao.findByCrm(crm); }

    @Transactional(readOnly = true)
    public List<String> getEspecialidades() { return dao.getEspecialidades(); }
}
