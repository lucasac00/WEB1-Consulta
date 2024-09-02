package org.consulta.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Paciente")
public class Paciente extends AbstractEntity<Long>{
    @NotBlank
    @Column(nullable = false, length = 64)
    private String email;
    @NotBlank
    @Column(nullable = false, length = 64)
    private String senha;
    @NotBlank
    @Column(nullable = false, length = 64)
    private String cpf;
    @NotBlank
    @Column(nullable = false, length = 64)
    private String nome;
    @NotBlank
    @Column(nullable = false, length = 64)
    private String telefone;
    @NotBlank
    @Column(nullable = false, length = 64)
    private String sexo;
    @NotBlank
    @Column(nullable = false, length = 64)
    private String dataNascimento;
    @NotBlank
    @Column(nullable = false, length = 64, unique = true)
    private String username;

    public Paciente() {

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}