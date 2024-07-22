<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="addNewPatient" /></title>
</head>
<body>
    <h2><fmt:message key="addNewPatient" /></h2>
    <form action="${pageContext.request.contextPath}/pacientes/criarPacientes" method="post">
        <label for="email"><fmt:message key="email" /></label>
        <input type="text" name="email" id="email" required><br>

        <label for="senha"><fmt:message key="password" /></label>
        <input type="password" name="senha" id="senha" required><br>

        <label for="cpf"><fmt:message key="cpf" /></label>
        <input type="text" name="cpf" id="cpf" required><br>

        <label for="nome"><fmt:message key="name" /></label>
        <input type="text" name="nome" id="nome" required><br>

        <label for="telefone"><fmt:message key="phone" /></label>
        <input type="text" name="telefone" id="telefone" required><br>

        <label for="sexo"><fmt:message key="gender" /></label>
        <input type="text" name="sexo" id="sexo" required><br>

        <label for="data_nascimento"><fmt:message key="birthday" /></label>
        <input type="text" name="data_nascimento" id="data_nascimento" required><br>

        <input type="submit" value=<fmt:message key='submitButton' />>
    </form>
</body>
</html>
