/* 
=====================================================================
    TODO:
        Acho que o email não deveria ser a chava principal, 
        mas sim o CPF/CRM, pois eles são "mais unicos" e são usados
        como base nas foreign key da Consulta por exemplo.
        (se fizer essa alteração, vai ter de alterar nos DAOs tambem)
        
        Outro problema é que a Consulta não tem primary key, e portanto,
        acredito que para identificar cada consulta teria de fazer a
        concatenação de cpf + crm + datetime. (ou sei la se precisa mesmo)

        DONE
=====================================================================
*/


DROP DATABASE IF EXISTS Agendamento;

CREATE DATABASE Agendamento;

USE Agendamento;

CREATE TABLE Paciente (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(50),
    senha VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    nome VARCHAR(50) NOT NULL,
    telefone VARCHAR(15),
    sexo CHAR(1),
    data_nascimento VARCHAR(20)
);

CREATE TABLE Medico (
    id BIGINT NOT NULL AUTO_INCREMENT  PRIMARY KEY,
    email VARCHAR(50),
    senha VARCHAR(50) NOT NULL,
    crm VARCHAR(20) UNIQUE NOT NULL,
    nome VARCHAR(50) NOT NULL,
    especialidade VARCHAR(50) NOT NULL
);

CREATE TABLE Consulta (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cpf_paciente VARCHAR(11) NOT NULL,
    crm_medico VARCHAR(20) NOT NULL,
    data_hora VARCHAR(30) NOT NULL,
    FOREIGN KEY (cpf_paciente) REFERENCES Paciente(cpf),
    FOREIGN KEY (crm_medico) REFERENCES Medico(crm)
);


CREATE TABLE Usuario (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    nome_display VARCHAR(100) NOT NULL
);
-- cargos: admin, paciente, medico
-- Adicionando Pacientes para o Banco de Dados:
INSERT INTO Paciente(email, senha, cpf, nome, telefone, sexo, data_nascimento) 
VALUES("pacienteRafael@mail", "senhaDoRafael", "45545678901", "Rafael", "551617348261", "M", "1993-07-13");

INSERT INTO Paciente (email, senha, cpf, nome, telefone, sexo, data_nascimento)
VALUES ("pacienteJoana@email.com", "senhaDaJoana", "97772012614", "Joana", "5511930910782", "F", "2000-01-15");

INSERT INTO Paciente (email, senha, cpf, nome, telefone, sexo, data_nascimento)
VALUES ("pacienteAna@email.com", "senhaDaAna", "54321678901", "Ana", "551198766271", "F", "1988-10-10");

INSERT INTO Paciente (email, senha, cpf, nome, telefone, sexo, data_nascimento)
VALUES ("pacienteGabriel@email.com", "senhaDoGabriel", "48829471629", "Gabriel", "551473129401", "M", "2004-10-28");

-- Adicionando Medicos para o Banco de Dados:
INSERT INTO Medico (email, senha, crm, nome, especialidade)
VALUES ("medicoJoao@email.com", "senhaDoJoao", "SP-36730", "Dr. João", "Cardiologia");

INSERT INTO Medico (email, senha, crm, nome, especialidade)
VALUES ("medicoDaniel@email.com", "senhaDoDaniel", "SC-86399", "Dr. Daniel", "Cardiologia");

INSERT INTO Medico (email, senha, crm, nome, especialidade)
VALUES ("medicaMaria@email.com", "senhaDaMaria", "AM-45082", "Dra. Maria", "Pediatria");

INSERT INTO Medico (email, senha, crm, nome, especialidade)
VALUES ("medicoPedro@email.com", "senhaDoPedro", "PR-54321", "Dr. Pedro", "Ortopedia");

INSERT INTO Medico (email, senha, crm, nome, especialidade)
VALUES ("medicaLarissa@email.com", "senhaDaLarissa", "RJ-18093", "Dra. Larissa", "Neurologia");

INSERT INTO Medico (email, senha, crm, nome, especialidade)
VALUES ("medicoGuilherme@email.com", "senhaDoGuilherme", "MG-48773", "Dr. Guilherme", "Neurologia");

-- Adicionando Consultas para o Banco de Dados
-- # Consulta do Paciente Rafael com o Medico Dr. Joao 
INSERT INTO Consulta (cpf_paciente, crm_medico, data_hora)
VALUES ("45545678901", "SP-36730", "2024-07-22 15:30:00");

-- # Consulta da Paciente Joana com a Medica Dra. Maria
INSERT INTO Consulta (cpf_paciente, crm_medico, data_hora)
VALUES ("97772012614", "AM-45082", "2024-07-22 15:30:00");

-- # Consulta da Paciente Ana com o Medico Dr. Pedro
INSERT INTO Consulta (cpf_paciente, crm_medico, data_hora)
VALUES ("54321678901", "PR-54321", "2024-07-23 18:15:00");

-- Adicionando Usuarios para o Banco de Dados
INSERT INTO Usuario (login, senha, cargo, nome_display)
VALUES ("lucasac", "123456", "admin", "Lucas Cardoso");

INSERT INTO Usuario (login, senha, cargo, nome_display)
VALUES ("rafael", "123456", "paciente", "Rafael");

INSERT INTO Usuario (login, senha, cargo, nome_display)
VALUES ("admin", "admin", "admin", "admin");
