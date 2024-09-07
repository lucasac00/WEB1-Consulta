package org.consulta.service.spec;

import java.util.List;

import org.consulta.domain.Medico;

public interface IMedicoService {

    //done
    Medico buscarPorId(long id);

    //done
    List<Medico> buscarPorEspecialidade(String especialidade);

    //done
    Medico buscarPorCrm(String crm);

    //done
    List<Medico> buscarTodos();

    //done
    List<String> getEspecialidades();

    //done
    void salvar(Medico medico);

    //done
    void excluir(Long id);

    //done
    void atualizar(Medico medico);

}