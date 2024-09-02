package org.consulta.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.consulta.domain.Medico;

@SuppressWarnings("unchecked")
public interface IMedicoDAO extends CrudRepository<Medico, Long>{

    //get
    Medico findById(long id);

    //getByespecialidade
    List<Medico> findByEspecialidade(String especialidade);

    //getByCrm
    Medico findByCrm(String crm);

    //getAll
    List<Medico> findAll();

    //getEspecialidadesDistintas
    @Query("SELECT DISTINCT m.especialidade FROM Medico m")
    List<String> getEspecialidades();

    //insert
    Medico save(Medico medico);

    //update
    //Medico update(Medico medico);

    //delete
    void deleteById(Long id);
}