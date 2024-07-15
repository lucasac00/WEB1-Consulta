drop database if exists Agendamento;

CREATE DATABASE Agendamento;
ente (
                          email VARCHAR(50) PRIMARY KEY,
                          senha VARCHAR(50) NOT NULL,
                          cpf VARCHAR(11) UNIQUE NOT NULL,
                          nome VARCHAR(50) NOT NULL,
                          telefone VARCHAR(15),
                          sexo CHAR(1),
                          data_nascimento VARCHAR(10) NOT NULL
);

CREATE TABLE Medico (
                        email VARCHAR(50) PRIMARY KEY,
                        senha VARCHAR(50) NOT NULL,
                        crm VARCHAR(20) UNIQUE NOT NULL,
                        nome VARCHAR(50) NOT NULL,
                        especialidade VARCHAR(50) NOT NULL
);

CREATE TABLE Consulta (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          cpf_paciente VARCHAR(11) NOT NULL,
                          crm_medico VARCHAR(20) NOT NULL,
                          data_hora VARCHAR(16) NOT NULL,
                          FOREIGN KEY (cpf_paciente) REFERENCES paciente(cpf),
                          FOREIGN KEY (crm_medico) REFERENCES medico(crm)
);



USE Agendamento;

CREATE TABLE Paci