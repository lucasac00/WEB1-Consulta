<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="editPatient" /></title>
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

        <input type="submit" value="<fmt:message key="save" />">
    </form>
</body>
</html>
