package org.consulta.dao;

import java.util.List;

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
    List<String> getEspecialidades();

    //insert
    Medico save(Medico medico);

    //update
    Medico update(Medico medico);

    //delete
    void deleteById(Long id);
}



/*
package org.consulta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.consulta.domain.Medico;

// Rever o update, pois do jeito que esta nao da para alterar o email

public class MedicoDAO extends GenericDAO {
    //Requisito R1
    public void insert(Medico medico) {

        String sql = "INSERT INTO Medico (email, senha, crm, nome, especialidade) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, medico.getEmail());
            statement.setString(2, medico.getSenha());
            statement.setString(3, medico.getCrm());
            statement.setString(4, medico.getNome());
            statement.setString(5, medico.getEspecialidade());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Requisito R3
    public List<Medico> getAll() {

        List<Medico> listaMedicos = new ArrayList<Medico>();

        String sql = "SELECT * from Medico";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String crm = resultSet.getString("crm");
                String nome = resultSet.getString("nome");
                String especialidade = resultSet.getString("especialidade");

                Medico medico = new Medico(id, email, senha, crm, nome, especialidade);
                listaMedicos.add(medico);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaMedicos;
    }

    // Requisito R4
    public List<String> getEspecialidadesDistintas() {

        List<String> especialidades = new ArrayList<>();

        String sql = "SELECT DISTINCT especialidade FROM Medico";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String especialidadeDiferente = resultSet.getString("especialidade");
                especialidades.add(especialidadeDiferente);
            }

            //System.out.println("aaaaaaa");
            //System.out.println(especialidades);

            resultSet.close();
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return especialidades;
        
    }

    //Requisito R4
    public List<Medico> getByEspecialidade (String especialidade){
        List<Medico> listaMedicos = new ArrayList<Medico>();

        String sql = "SELECT * FROM Medico WHERE especialidade = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, especialidade);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String crm = resultSet.getString("crm");
                String nome = resultSet.getString("nome");

                Medico medico = new Medico(id, email, senha, crm, nome, especialidade);
                listaMedicos.add(medico);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaMedicos;
    }
    //Requisito R1
    public void delete(Medico medico) {

        String crmMedico = medico.getCrm();

        ConsultaDAO consultaDao = new ConsultaDAO();
        consultaDao.deleteByCrm(crmMedico);
        
        String sql = "DELETE FROM Medico where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, medico.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Requisito R1
    public void update(Medico medico) {
        String sql = "UPDATE Medico SET email = ?, senha = ?, crm = ?, nome = ?, especialidade = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, medico.getEmail());
            statement.setString(2, medico.getSenha());
            statement.setString(3, medico.getCrm());
            statement.setString(4, medico.getNome());
            statement.setString(5, medico.getEspecialidade());
            statement.setLong(6, medico.getId());
            
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Requisito R1
    public Medico get(Long id) {
        Medico medico = null;
        
        String sql = "SELECT * from Medico where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email  =resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String crm = resultSet.getString("crm");
                String nome = resultSet.getString("nome");
                String especialidade = resultSet.getString("especialidade");

                medico = new Medico(id, email, senha, crm, nome, especialidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medico;
    }
    //Utilizado na criação de consulta
    public Medico getByCrm(String crm) {
        Medico medico = null;

        String sql = "SELECT * from Medico where crm = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, crm);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email  =resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String especialidade = resultSet.getString("especialidade");

                medico = new Medico(id, email, senha, crm, nome, especialidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medico;
    }
}

 */