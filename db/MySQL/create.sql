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
=====================================================================
*/


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
VALUES ("medicaMaria@email.com", "senhaDaMaria", "AM-45082", "Dra. Maria", "Pediatria");

INSERT INTO Medico (email, senha, crm, nome, especialidade)
VALUES ("medicoPedro@email.com", "senhaDoPedro", "PR-54321", "Dr. Pedro", "Ortopedia");

INSERT INTO Medico (email, senha, crm, nome, especialidade)
VALUES ("medicoLarissa@email.com", "senhaDaLarissa", "RJ-18093", "Dra. Larissa", "Neurologia");



-- Adicionando Consultas para o Banco de Dados
# Consulta do Paciente Rafael com o Medico Dr. Joao
INSERT INTO Consulta (cpf_paciente, crm_medico, data_hora)
VALUES ("45545678901", "SP-36730", "2024-07-22 15:30:00");

# Consulta da Paciente Joana com a Medica Dra. Maria
INSERT INTO Consulta (cpf_paciente, crm_medico, data_hora)
VALUES ("97772012614", "AM-45082", "2024-07-22 15:30:00");

# Consulta da Paciente Ana com o Medico Dr. Pedro
INSERT INTO Consulta (cpf_paciente, crm_medico, data_hora)
VALUES ("54321678901", "PR-54321", "2024-07-23 18:15:00");

