package org.consulta.service.spec;

import java.util.List;

import org.consulta.domain.Paciente;

public interface IPacienteService {

    void salvar(Paciente paciente);

    void atualizar(Paciente paciente);

    void excluir(Long id);

    Paciente buscarPorId(Long id);

    Paciente buscarPorCpf(String cpf);

    List<Paciente> buscarTodos();

    Paciente buscarPorUsername(String username);
}
