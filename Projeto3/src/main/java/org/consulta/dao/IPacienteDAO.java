package org.consulta.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.consulta.domain.Medico;
import org.consulta.domain.Paciente;

@SuppressWarnings("unchecked")
public interface IPacienteDAO extends CrudRepository<Paciente, Long>{
    //insert
    Paciente save(Paciente paciente);

    //update
    //Paciente update(Paciente paciente);

    //getAll
    List<Paciente> findAll();

    //get
    Paciente findById(long id);

    //getByCpf
    Paciente findByCpf(String cpf);

    //getByUsername
    Paciente findByUsername(String username);

    //getByEmail
    Paciente findByEmail(String email);

    //delete
    void deleteById(Long id);
}
