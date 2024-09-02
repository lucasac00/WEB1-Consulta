package org.consulta.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Medico")
public class Medico extends AbstractEntity<Long> {

    @NotBlank(message = "{NotBlank.medico.email}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String email;

    @NotBlank
    @Column(nullable = false, length = 60, unique = true)
    private String username;

    @NotBlank(message = "{NotBlank.medico.Password}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String password;

    @NotBlank(message = "{NotBlank.medico.crm}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String crm;

    @NotBlank(message = "{NotBlank.medico.nome}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String name;

    @NotBlank(message = "{NotBlank.medico.especialidade}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String especialidade;


    public String getEmail() {
        return email;
    }

    public String getUsername() { return username; }

    public String getPassword() {
        return password;
    }

    public String getCrm() {
        return crm;
    }

    public String getName() {
        return name;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}