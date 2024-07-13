drop database if exists Agendamento;

CREATE DATABASE Agendamento;

USE Agendamento;

CREATE TABLE Paciente (
                          email VARCHAR(50) PRIMARY KEY,
                          senha VARCHAR(50) NOT NULL,
                          cpf VARCHAR(11) UNIQUE NOT NULL,
                          nome VARCHAR(50) NOT NULL,
                          telefone VARCHAR(15),
                          sexo CHAR(1),
                          data_nascimento DATE
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
                          data_hora DATETIME NOT NULL,
                          FOREIGN KEY (cpf_paciente) REFERENCES paciente(cpf),
                          FOREIGN KEY (crm_medico) REFERENCES medico(crm)
);


