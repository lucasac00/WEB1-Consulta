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
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Consulta c WHERE (c.crm = :crm AND c.dataHora = :dataHora) OR (c.dataHora = :dataHora AND c.cpf = :cpf)")
    boolean checkValidity(@Param("crm") String crm, @Param("cpf") String cpf, @Param("dataHora") String dataHora);

}

/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.consulta.domain.Consulta;

public class ConsultaDAO extends GenericDAO {
    //CRUD Consulta - Adicionar check se já não existe consulta do mesmo médico e paciente no mesmo horário
    public void insert(Consulta consulta) {

        String sql = "INSERT INTO Consulta (cpf_paciente, crm_medico, data_hora) VALUES (?, ?, ?)";
        // TODO: Conferir se existe CPF e CRM no banco de dados antes de criar consulta
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, consulta.getCpfPaciente());
            statement.setString(2, consulta.getCrmMedico());
            statement.setString(3, consulta.getDataHora());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //CRUD Consulta
    public List<Consulta> getAll() {

        List<Consulta> listaConsultas = new ArrayList<Consulta>();

        String sql = "SELECT * from Consulta";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cpfPaciente = resultSet.getString("cpf_paciente");
                String crmMedico = resultSet.getString("crm_medico");
                String dataHora = resultSet.getString("data_hora");

                Consulta consulta = new Consulta(id, cpfPaciente, crmMedico, dataHora);
                listaConsultas.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }
    //Requisito R6 - Todas as consultas de um paciente
    public List<Consulta> getByCpf(String cpf) {
        List<Consulta> listaConsultas = new ArrayList<Consulta>();

        String sql = "SELECT * from Consulta WHERE cpf_paciente = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cpfPaciente = resultSet.getString("cpf_paciente");
                String crmMedico = resultSet.getString("crm_medico");
                String dataHora = resultSet.getString("data_hora");

                Consulta consulta = new Consulta(id, cpfPaciente, crmMedico, dataHora);
                listaConsultas.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }
    //Requisito R8 - Todas as consultas de um médico
    public List<Consulta> getByCrm(String crm) {
        List<Consulta> listaConsultas = new ArrayList<Consulta>();

        String sql = "SELECT * from Consulta WHERE crm_medico = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, crm);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cpfPaciente = resultSet.getString("cpf_paciente");
                String crmMedico = resultSet.getString("crm_medico");
                String dataHora = resultSet.getString("data_hora");

                Consulta consulta = new Consulta(id, cpfPaciente, crmMedico, dataHora);
                listaConsultas.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }


    public void deleteByCrm(String crmMedico) {
        String sql = "DELETE FROM Consulta WHERE crm_medico = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, crmMedico);
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkValidity(String crm, String dataHora, String cpf) {
        String sql = "SELECT * FROM Consulta WHERE (crm_medico = ? AND data_hora = ?) OR (data_hora = ? AND cpf_paciente = ?)";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, crm);
            statement.setString(2, dataHora);
            statement.setString(3, dataHora);
            statement.setString(4, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return false; // Já existe consulta
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true; // Não existe consulta, pode criar
    }


    //CRUD Consulta
    public void delete(Consulta consulta) {
        String sql = "DELETE FROM Consulta where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, consulta.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //CRUD Consulta
    public void update(Consulta consulta) {
        String sql = "UPDATE Consulta SET cpf_paciente = ?, crm_medico = ?, data_hora = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, consulta.getCpfPaciente());
            statement.setString(2, consulta.getCrmMedico());
            statement.setString(3, consulta.getDataHora());
            statement.setLong(4, consulta.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //CRUD Consulta
    public Consulta get(int id) {
        Consulta consulta = null;

        String sql = "SELECT * from Consulta where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cpfPaciente = resultSet.getString("cpfPaciente");
                String crmMedico = resultSet.getString("crmMedico");
                String dataHora = resultSet.getString("dataHora");

                consulta = new Consulta(id, cpfPaciente, crmMedico, dataHora);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consulta;
    }

}*/