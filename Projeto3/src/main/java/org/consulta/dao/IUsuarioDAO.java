package org.consulta.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.consulta.domain.Usuario;

@SuppressWarnings("unchecked")
public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {

    //get
    Usuario findById(long id);

    //getByLogin
    Usuario findByUsername(String username);

    //getByDocumento
    Usuario findByCpf(String cpf);

    //getAll
    List<Usuario> findAll();

    //insert
    Usuario save(Usuario usuario);

    //update
    //Usuario update(Usuario usuario);

    //delete
    void deleteById(Long id);

    @Query("SELECT user FROM Usuario user WHERE user.username = :username")
    public Usuario getUserByUsername(@Param("username") String username);
}
