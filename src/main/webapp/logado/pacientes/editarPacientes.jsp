<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Médico</title>
</head>
<body>
    <h2>Editar Médico</h2>
    <form action="${pageContext.request.contextPath}/pacientes/editarPacientes" method="post">
        <input type="hidden" name="id" value="${paciente.id}">
        
        <label for="email">Email:</label>
        <input type="text" name="email" id="email" value="${paciente.email}" required><br>

        <label for="senha">Senha:</label>
        <input type="password" name="senha" id="senha" value="${paciente.senha}" required><br>

        <label for="cpf">CPF:</label>
        <input type="text" name="cpf" id="cpf" value="${paciente.cpf}" required><br>

        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" value="${paciente.nome}" required><br>

        <label for="telefone">Telefone::</label>
        <input type="text" name="telefone" id="telefone" value="${paciente.telefone}" required><br>

        <label for="sexo">Sexo:</label>
        <input type="text" name="sexo" id="sexo" value="${paciente.sexo}" required><br>

        <label for="data_nascimento">Data de Nascimento:</label>
        <input type="text" name="data_nascimento" id="data_nascimento" value="${paciente.dataNascimento}" required><br>

        <input type="submit" value="Salvar">
    </form>
</body>
</html>
