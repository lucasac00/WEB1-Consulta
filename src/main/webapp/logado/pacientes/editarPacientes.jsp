<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="editPatient" /></title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #c0d9ff, #1a73e8);
            color: #333;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            padding: 20px;
        }

        h2 {
            color: white;
            margin-bottom: 20px;
        }

        form {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
            display: flex;
            flex-direction: column;
        }

        label {
            margin-top: 10px;
            color: #333;
            font-weight: bold;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            background: #1a73e8;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.3s ease;
        }

        input[type="submit"]:hover {
            background: #1f15e5;
        }
    </style>
</head>
<body>
    <h2><fmt:message key="editPatient" /></h2>
    <form action="${pageContext.request.contextPath}/pacientes/editarPacientes" method="post">
        <input type="hidden" name="id" value="${paciente.id}">
        <input type="hidden" name="ogcpf" value="${ogcpf}" />
        
        <label for="email"><fmt:message key="email" /></label>
        <input type="text" name="email" id="email" value="${paciente.email}" required><br>

        <label for="senha"><fmt:message key="password"/></label>
        <input type="password" name="senha" id="senha" value="${paciente.senha}" required><br>

        <label for="cpf"><fmt:message key="cpf" /></label>
        <input type="text" name="cpf" id="cpf" value="${paciente.cpf}" required><br>

        <label for="nome"><fmt:message key="name" /></label>
        <input type="text" name="nome" id="nome" value="${paciente.nome}" required><br>

        <label for="telefone"><fmt:message key="phone" /></label>
        <input type="text" name="telefone" id="telefone" value="${paciente.telefone}" required><br>

        <label for="sexo"><fmt:message key="gender" /></label>
        <input type="text" name="sexo" id="sexo" value="${paciente.sexo}" required><br>

        <label for="data_nascimento"><fmt:message key="birthday" /></label>
        <input type="text" name="data_nascimento" id="data_nascimento" value="${paciente.dataNascimento}" required><br>

        <input type="submit" value=<fmt:message key='save' />>
    </form>
</body>
</html>
