package org.consulta.dao;

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

    public void insert(Consulta consulta) {

        String sql = "INSERT INTO Consulta (id, cpfPaciente, crmMedico, dataHora) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setInt(1, consulta.getId());
            statement.setString(2, consulta.getCpfPaciente());
            statement.setString(3, consulta.getCrmMedico());
            statement.setString(4, consulta.getDataHora());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consulta> getAll() {

        List<Consulta> listaConsultas = new ArrayList<Consulta>();

        String sql = "SELECT * from Consulta";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cpfPaciente = resultSet.getString("cpfPaciente");
                String crmMedico = resultSet.getString("crmMedico");
                String dataHora = resultSet.getString("dataHora");

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

    public void delete(Consulta consulta) {
        String sql = "DELETE FROM Consulta where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, consulta.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Consulta consulta) {
        String sql = "UPDATE Consulta SET cpfPaciente = ?, crmMedico = ?, dataHora = ?";
        sql += " WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, consulta.getCpfPaciente());
            statement.setString(2, consulta.getCrmMedico());
            statement.setString(3, consulta.getDataHora());
            statement.setInt(4, consulta.getId());
            
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Consulta get(int id) {
        Consulta consulta = null;
        
        String sql = "SELECT * from Consulta where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setInt(1, id);
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
}