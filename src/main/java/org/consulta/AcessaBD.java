package org.consulta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcessaBD {

    public static void main(String[] args) {
        try {
            // Setup para uso do banco de dados MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Agendamento";
            Connection con = DriverManager.getConnection(url, "root", "root");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Medico");
            while (rs.next()) {
                long id = rs.getLong("id");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String crm = rs.getString("crm");
                String nome = rs.getString("nome");
                String especialidade = rs.getString("especialidade");

                System.out.print("ID: " + id);
                System.out.print(", Email: " + email);
                System.out.print(", Senha: " + senha);
                System.out.print(", CRM: " + crm);
                System.out.print(", Nome: " + nome);
                System.out.println(", Especialidade: " + especialidade);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("A classe do driver de conexão não foi encontrada!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("O comando SQL não pode ser executado!");
            e.printStackTrace();
        }
    }
}
