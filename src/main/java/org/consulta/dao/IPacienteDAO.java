package org.consulta.dao;

import java.util.List;

import org.consulta.domain.Consulta;
import org.springframework.data.repository.CrudRepository;

import org.consulta.domain.Paciente;

@SuppressWarnings("unchecked")
public interface IPacienteDAO extends CrudRepository<Paciente, Long>{
    //insert
    Paciente save(Paciente paciente);

    //update
    Paciente update(Paciente paciente);

    //getAll
    List<Paciente> findAll();

    //get
    Paciente get(Long id);

    //getByCpf
    Paciente findByCpf(String cpf);

    //delete
    void deleteById(Long id);
}

/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.consulta.domain.Paciente;

// Rever o update, pois do jeito que esta nao da para alterar o email

public class PacienteDAO extends GenericDAO {
    //Requisito R2
    public void insert(Paciente paciente) {

        String sql = "INSERT INTO Paciente (email, senha, cpf, nome, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, paciente.getEmail());
            statement.setString(2, paciente.getSenha());
            statement.setString(3, paciente.getCpf());
            statement.setString(4, paciente.getNome());
            statement.setString(5, paciente.getTelefone());
            statement.setString(6, paciente.getSexo());
            statement.setString(7, paciente.getDataNascimento());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Requisito R2
    public List<Paciente> getAll() {

        List<Paciente> listaPacientes = new ArrayList<Paciente>();

        String sql = "SELECT * from Paciente";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String data_nascimento = resultSet.getString("data_nascimento");

                Paciente paciente = new Paciente(id, email, senha, cpf, nome, telefone, sexo, data_nascimento);
                listaPacientes.add(paciente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPacientes;
    }
    //Requisito R2
    public void delete(Paciente paciente) {
        String sql = "DELETE FROM Paciente where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, paciente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Requisito R2
    public void update(Paciente paciente) {
        String sql = "UPDATE Paciente SET email = ?, senha = ?, cpf = ?, nome = ?, telefone = ?, sexo = ?, data_nascimento = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, paciente.getEmail());
            statement.setString(2, paciente.getSenha());
            statement.setString(3, paciente.getCpf());
            statement.setString(4, paciente.getNome());
            statement.setString(5, paciente.getTelefone());
            statement.setString(6, paciente.getSexo());
            statement.setString(7, paciente.getDataNascimento());
            statement.setLong(8, paciente.getId());
            
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Requisito R2
    public Paciente get(Long id) {
        Paciente paciente = null;
        
        String sql = "SELECT * from Paciente where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String data_nascimento = resultSet.getString("data_nascimento");

                paciente = new Paciente(id, email, senha, cpf, nome, telefone, sexo, data_nascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paciente;
    }
    //Utilizado no requisito R6
    public Paciente getByCpf(String cpf) {
        Paciente paciente = null;

        String sql = "SELECT * from Paciente where cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String data_nascimento = resultSet.getString("data_nascimento");

                paciente = new Paciente(id, email, senha, cpf, nome, telefone, sexo, data_nascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paciente;
    }
}*/