package org.consulta.service.impl;

import org.consulta.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.consulta.dao.IPacienteDAO;
import org.consulta.domain.Paciente;
import org.consulta.service.spec.IPacienteService;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class PacienteService {

    @Autowired
    IPacienteDAO dao;

    public void salvar(Paciente paciente) { dao.save(paciente); }

    public void atualizar(Paciente paciente) { dao.save(paciente); }

    public void excluir(Long id) { dao.deleteById(id); }

    @Transactional(readOnly = true)
    public List<Paciente> buscarTodos() { return dao.findAll(); }

    @Transactional(readOnly = true)
    public Paciente buscarPorId(long id) { return dao.findById(id); }

    @Transactional(readOnly = true)
    public Paciente buscarPorCpf(String cpf) { return dao.findByCpf(cpf); }
}
