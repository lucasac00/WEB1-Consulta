package org.consulta.domain;

public class Consulta {
    private int id;
    private String cpfPaciente;
    private String crmMedico;
    private String dataHora;

    public Consulta(int id) {
        this.id = id;
    }

    public Consulta(String cpfPaciente, String crmMedico, String dataHora) {
        super();
        this.cpfPaciente = cpfPaciente;
        this.crmMedico = crmMedico;
        this.dataHora = dataHora;
    }

    public Consulta(int id, String cpfPaciente, String crmMedico, String dataHora) {
        super();
        this.id = id;
        this.cpfPaciente = cpfPaciente;
        this.crmMedico = crmMedico;
        this.dataHora = dataHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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