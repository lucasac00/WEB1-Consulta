package org.consulta.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Consulta")
public class Consulta extends AbstractEntity<Long> {

    @NotBlank
    @Column(nullable = false, length = 64)
    private String cpf;
    @NotBlank
    @Column(nullable = false, length = 64)
    private String crm;
    @NotBlank
    @Column(nullable = false, length = 64)
    private String dataHora;

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