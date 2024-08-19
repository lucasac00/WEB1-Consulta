package org.consulta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.consulta.dao.IConsultaDAO;
import org.consulta.domain.Consulta;
import org.consulta.service.spec.IConsultaService;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class ConsultaService implements IConsultaService{

    @Autowired
    private IConsultaDAO dao;

    public void salvar(Consulta consulta) { dao.save(consulta); }

    public void atualizar(Consulta consulta) { dao.save(consulta); }

    public void excluir(Long id) { dao.deleteById(id); }

    public void excluirPorCrm(String crm) { dao.deleteByCrm(crm); }

    @Override
    @Transactional(readOnly = true)
    public List<Consulta> buscarTodos() { return dao.findAll(); }

    @Override
    @Transactional(readOnly = true)
    public Consulta buscarPorId(Long id) { return dao.findById(id).orElse(null); }

    @Override
    @Transactional(readOnly = true)
    public List<Consulta> buscarPorCpf(String cpf) { return dao.findByCpf(cpf); }

    @Override
    @Transactional(readOnly = true)
    public List<Consulta> buscarPorCrm(String crm) { return dao.findByCrm(crm); }

    @Override
    @Transactional(readOnly = true)
    public boolean checkValidity(String crm, String cpf, String dataHora) {
        return dao.checkValidity(crm, cpf, dataHora);
    }
}
