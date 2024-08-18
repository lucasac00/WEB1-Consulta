package org.consulta.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Consulta")
public class Consulta extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false, length = 64)
    private String cpf;
    @NotBlank
    @Column(nullable = false, length = 64)
    private String crm;
    @NotBlank
    @Column(nullable = false, length = 64)
    private String dataHora;

    public Consulta(Long id) {
        this.id = id;
    }

    public Consulta(String cpf, String crm, String dataHora) {
        super();
        this.cpf = cpf;
        this.crm = crm;
        this.dataHora = dataHora;
    }

    public Consulta(Long id, String cpf, String crm, String dataHora) {
        super();
        this.id = id;
        this.cpf = cpf;
        this.crm = crm;
        this.dataHora = dataHora;
    }

    public Consulta() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpfPaciente) {
        this.cpf = cpfPaciente;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crmMedico) {
        this.crm = crmMedico;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}