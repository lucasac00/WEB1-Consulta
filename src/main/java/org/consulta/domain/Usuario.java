package org.consulta.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
public class Usuario extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @NotBlank
    @Column(nullable = false, length = 64)
    private String password;

    @NotBlank
    @Column(nullable = false, length = 60)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 14)
    private String CPF;

    @NotBlank
    @Column(nullable = false, length = 10)
    private String role;

    @Column(nullable = false)
    private boolean enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

/*package org.consulta.domain;

public class Usuario {
    private Long id;
    private String login;
    private String senha;
    private String cargo;
    private String nome_display;
    private String documento;

    public Usuario(Long id) {this.id = id;}

    public Usuario(String login, String senha, String cargo, String nome_display, String documento) {
        super();
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
        this.nome_display = nome_display;
        this.documento = documento;
    }

    public Usuario(Long id, String login, String senha, String cargo, String nome_display, String documento) {
        super();
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
        this.nome_display = nome_display;
        this.documento = documento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNome() {
        return nome_display;
    }

    public void setNome(String nome_display) {
        this.nome_display = nome_display;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
*/