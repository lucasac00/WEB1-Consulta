package org.consulta.dao;

import org.consulta.domain.Usuario;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends GenericDAO {

    public void insert (Usuario usuario){
        String sql = "INSERT INTO Usuario (login, senha, cargo, nome_display) VALUES (?,?,?,?,?)";

        try {
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getCargo());
            statement.setString(4, usuario.getNome());
            statement.executeUpdate();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> getAll() {
        List<Usuario> list = new ArrayList<>();

        String sql = "SELECT * FROM Usuario";

        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(sql);

            while (set.next()) {
                long id = set.getLong("id");
                String login = set.getString("login");
                String senha = set.getString("senha");
                String cargo = set.getString("cargo");
                String nome_display = set.getString("nome_display");

                Usuario usuario = new Usuario(id, login, senha, cargo, nome_display);
                list.add(usuario);
            }

            set.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void delete (Usuario usuario) {
        String sql = "DELETE FROM Usuario WHERE id = ?";

        try {
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, usuario.getId());
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update (Usuario usuario) {
        String sql = "UPDATE Usuario SET login = ?, senha = ?, cargo = ?, nome_display = ? WHERE id = ?";

        try {
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getCargo());
            statement.setString(4, usuario.getNome());
            statement.setLong(5, usuario.getId());

            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario get (Long id) {
        Usuario usuario = null;

        String sql = "SELECT * FROM Usuario WHERE id = ?";

        try {
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                String login = set.getString("login");
                String senha = set.getString("senha");
                String cargo = set.getString("cargo");
                String nome = set.getString("nome_display");

                usuario = new Usuario(id, login, senha, cargo, nome);
            }

            set.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuario;
    }

    public Usuario getByLogin (String login) {
        Usuario usuario = null;

        String sql = "SELECT * FROM Usuario WHERE login = ?";

        try {
            Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, login);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                Long id = set.getLong("id");
                String senha = set.getString("senha");
                String cargo = set.getString("cargo");
                String nome = set.getString("nome_display");

                usuario = new Usuario(id, login, senha, cargo, nome);
            }

            set.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuario;
    }
}
