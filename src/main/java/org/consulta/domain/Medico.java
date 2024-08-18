package org.consulta.domain;

import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Medico")
public class Medico extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{NotBlank.medico.email}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String email;

    @NotBlank(message = "{NotBlank.medico.senha}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String senha;

    @NotBlank(message = "{NotBlank.medico.crm}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String crm;

    @NotBlank(message = "{NotBlank.medico.nome}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String nome;

    @NotBlank(message = "{NotBlank.medico.especialidade}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String especialidade;


    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCrm() {
        return crm;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}



/*package org.consulta.domain;


public class Medico {
    private Long id;
    private String email;
    private String senha;
    private String crm;
    private String nome;
    private String especialidade;

    public Medico() {
        
    }

    public Medico(Long id) {
        this.id = id;
    }

    public Medico(String email, String senha, String crm, String nome, String especialidade) {
        super();
        this.email = email;
        this.senha = senha;
        this.crm = crm;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public Medico(Long id, String email, String senha, String crm, String nome, String especialidade) {
        super();
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.crm = crm;
        this.nome = nome;
        this.especialidade = especialidade;
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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}*/