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
    nome_display VARCHAR(100) NOT NULL,
    documento VARCHAR(50)
);
-- cargos: admin, paciente, medico
-- Adicionando Pacientes para o Banco de Dados:
INSERT INTO Paciente (email, senha, cpf, nome, telefone, sexo, data_nascimento)
VALUES ("pacienteRafael@mail", "senhaDoRafael", "45545678901", "Rafael", "551617348261", "M", "1993-07-13");

INSERT INTO Usuario (login, senha, cargo, nome_display, documento)
VALUES ("pacienteRafael", "senhaDoRafael", "paciente", "Rafael", "45545678901");

INSERT INTO Paciente (email, senha, cpf, nome, telefone, sexo, data_nascimento)
VALUES ("pacienteJoana@email.com", "senhaDaJoana", "97772012614", "Joana", "5511930910782", "F", "2000-01-15");

INSERT INTO Usuario (login, senha, cargo, nome_display, documento)
VALUES ("pacienteJoana", "senhaDaJoana", "paciente", "Joana", "97772012614");

INSERT INTO Paciente (email, senha, cpf, nome, telefone, sexo, data_nascimento)
VALUES ("pacienteAna@email.com", "senhaDaAna", "54321678901", "Ana", "551198766271", "F", "1988-10-10");

INSERT INTO Usuario (login, senha, cargo, nome_display, documento)
VALUES ("pacienteAna", "senhaDaAna", "paciente", "Ana", "54321678901");

INSERT INTO Paciente (email, senha, cpf, nome, telefone, sexo, data_nascimento)
VALUES ("pacienteGabriel@email.com", "senhaDoGabriel", "48829471629", "Gabriel", "551473129401", "M", "2004-10-28");

INSERT INTO Usuario (login, senha, cargo, nome_display, documento)
VALUES ("pacienteGabriel", "senhaDoGabriel", "paciente", "Gabriel", "48829471629");

-- Adicionando Medicos para o Banco de Dados:
INSERT INTO Medico (email, senha, crm, nome, especialidade)
VALUES ("medicoJoao@email.com", "senhaDoJoao", "SP-36730", "Dr. João", "Cardiologia");

INSERT INTO Usuario (login, senha, cargo, nome_display, documento)
VALUES ("medicoJoao", "senhaDoJoao", "medico", "Dr. João", "SP-36730");

INSERT INTO Medico (email, senha, crm, nome, especialidade)
VALUES ("medicoDaniel@email.com", "senhaDoDaniel", "SC-86399", "Dr. Daniel", "Cardiologia");

INSERT INTO Usuario (login, senha, cargo, nome_display, documento)
VALUES ("medicoDaniel", "senhaDoDaniel", "medico", "Dr. Daniel", "SC-86399");

INSERT INTO Medico (email, senha, crm, nome, especialidade)
VALUES ("medicaMaria@email.com", "senhaDaMaria", "AM-45082", "Dra. Maria", "Pediatria");

INSERT INTO Usuario (login, senha, cargo, nome_display, documento)
VALUES ("medicaMaria", "senhaDaMaria", "medico", "Dra. Maria", "AM-45082");

INSERT INTO Medico (email, senha, crm, nome, especialidade)
VALUES ("medicoPedro@email.com", "senhaDoPedro", "PR-54321", "Dr. Pedro", "Ortopedia");

INSERT INTO Usuario (login, senha, cargo, nome_display, documento)
VALUES ("medicoPedro", "senhaDoPedro", "medico", "Dr. Pedro", "PR-54321");

INSERT INTO Medico (email, senha, crm, nome, especialidade)
VALUES ("medicaLarissa@email.com", "senhaDaLarissa", "RJ-18093", "Dra. Larissa", "Neurologia");

INSERT INTO Usuario (login, senha, cargo, nome_display, documento)
VALUES ("medicaLarissa", "senhaDaLarissa", "medico", "Dra. Larissa", "RJ-18093");

INSERT INTO Medico (email, senha, crm, nome, especialidade)
VALUES ("medicoGuilherme@email.com", "senhaDoGuilherme", "MG-48773", "Dr. Guilherme", "Neurologia");

INSERT INTO Usuario (login, senha, cargo, nome_display, documento)
VALUES ("medicoGuilherme", "senhaDoGuilherme", "medico", "Dr. Guilherme", "MG-48773");

-- Adicionando Consultas para o Banco de Dados
-- Consulta do Paciente Rafael com o Medico Dr. Joao
INSERT INTO Consulta (cpf_paciente, crm_medico, data_hora)
VALUES ("45545678901", "SP-36730", "2024-07-22T15:30");

-- Consulta da Paciente Joana com o Medico Dr. Joao
INSERT INTO Consulta (cpf_paciente, crm_medico, data_hora)
VALUES ("97772012614", "SP-36730", "2024-07-22T21:00");

-- Consulta da Paciente Joana com a Medica Dra. Maria
INSERT INTO Consulta (cpf_paciente, crm_medico, data_hora)
VALUES ("97772012614", "AM-45082", "2024-07-22T16:30");

-- Consulta da Paciente Ana com o Medico Dr. Pedro
INSERT INTO Consulta (cpf_paciente, crm_medico, data_hora)
VALUES ("54321678901", "PR-54321", "2024-07-23T18:00");

-- Adicionando Admins para o Banco de Dados
INSERT INTO Usuario (login, senha, cargo, nome_display, documento)
VALUES ("lucasac", "123456", "admin", "Lucas Cardoso", NULL);

INSERT INTO Usuario (login, senha, cargo, nome_display, documento)
VALUES ("admin", "admin", "admin", "admin", NULL);