package org.consulta.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.consulta.domain.Consulta;
import org.springframework.data.repository.query.Param;

@SuppressWarnings("unchecked")
public interface IConsultaDAO extends CrudRepository<Consulta, Long>{
    //insert
    Consulta save(Consulta consulta);

    //update
    //Consulta update(Consulta consulta);

    //getAll
    List<Consulta> findAll();

    //get
    Consulta findById(long id);

    //getByCpf
    List<Consulta> findByCpf(String cpf);

    //getByCrm
    List<Consulta> findByCrm(String crm);

    //deleteByCrm
    void deleteByCrm(String crm);

    //delete
    void deleteById(Long id);

    //checkValidity
    @Query("SELECT COUNT(c) > 0 FROM Consulta c WHERE (c.crm = :crm AND c.dataHora = :dataHora) OR (c.dataHora = :dataHora AND c.cpf = :cpf)")
    boolean checkValidity(@Param("crm") String crm, @Param("cpf") String cpf, @Param("dataHora") String dataHora);

}
