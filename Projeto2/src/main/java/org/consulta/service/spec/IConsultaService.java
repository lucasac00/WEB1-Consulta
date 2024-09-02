package org.consulta.service.spec;

import java.util.List;

import org.consulta.domain.Consulta;

public interface IConsultaService {

    void salvar(Consulta consulta);

    void atualizar(Consulta consulta);

    void excluir(Long id);

    void excluirPorCrm(String crm);

    List<Consulta> buscarTodos();

    Consulta buscarPorId(Long id);

    List<Consulta> buscarPorCpf(String cpf);

    List<Consulta> buscarPorCrm(String crm);

    boolean checkValidity(String crm, String cpf, String dataHora);
}
