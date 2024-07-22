<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Adicionar Paciente</title>
</head>
<body>
    <h2>Adicionar Paciente</h2>
    <form action="${pageContext.request.contextPath}/pacientes/criarPacientes" method="post">
        <label for="email">Email:</label>
        <input type="text" name="email" id="email" required><br>

        <label for="senha">Senha:</label>
        <input type="password" name="senha" id="senha" required><br>

        <label for="crm">CPF:</label>
        <input type="text" name="cpf" id="cpf" required><br>

        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" required><br>

        <label for="telefone">Telefone:</label>
        <input type="text" name="telefone" id="telefone" required><br>

        <label for="sexo">Sexo:</label>
        <input type="text" name="sexo" id="sexo" required><br>

        <label for="data_nascimento">Data de Nascimento:</label>
        <input type="text" name="data_nascimento" id="data_nascimento" required><br>

        <input type="submit" value="Adicionar">
    </form>
</body>
</html>
