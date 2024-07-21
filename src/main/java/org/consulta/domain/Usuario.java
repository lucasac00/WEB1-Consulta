package org.consulta.domain;

public class Usuario {
    private Long id;
    private String email;
    private String senha;
    private String cargo;
    private String nome_display;

    public Usuario(Long id) {this.id = id;}

    public Usuario(String email, String senha, String cargo, String nome_display) {
        super();
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.nome_display = nome_display;
    }

    public Usuario(Long id, String email, String senha, String cargo, String nome_display) {
        super();
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.nome_display = nome_display;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
