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
    private String cpfPaciente;
    @NotBlank
    @Column(nullable = false, length = 64)
    private String crmMedico;
    @NotBlank
    @Column(nullable = false, length = 64)
    private String dataHora;

    public Consulta(Long id) {
        this.id = id;
    }

    public Consulta(String cpfPaciente, String crmMedico, String dataHora) {
        super();
        this.cpfPaciente = cpfPaciente;
        this.crmMedico = crmMedico;
        this.dataHora = dataHora;
    }

    public Consulta(Long id, String cpfPaciente, String crmMedico, String dataHora) {
        super();
        this.id = id;
        this.cpfPaciente = cpfPaciente;
        this.crmMedico = crmMedico;
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

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(String crmMedico) {
        this.crmMedico = crmMedico;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}