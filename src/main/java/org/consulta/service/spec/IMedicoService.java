package org.consulta.service.spec;

import java.util.List;

import org.consulta.domain.Medico;

public interface IMedicoService {

    Medico buscarPorId(Long id);

    List<Medico> buscarTodos();

    void salvar(Medico medico);

    void excluir(Long id);

}