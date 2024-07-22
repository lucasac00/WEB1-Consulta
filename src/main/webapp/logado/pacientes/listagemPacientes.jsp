<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listagem de Pacientes</title>
</head>
<body>
    <h2>Listagem de Pacientes</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Email</th>
            <th>CPF</th>
            <th>Nome</th>
            <th>Telefone</th>
            <th>Sexo</th>
            <th>Data de Nascimento</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="paciente" items="${listaPacientes}">
            <tr>
                <td>${paciente.id}</td>
                <td>${paciente.email}</td>
                <td>${paciente.cpf}</td>
                <td>${paciente.nome}</td>
                <td>${paciente.telefone}</td>
                <td>${paciente.sexo}</td>
                <td>${paciente.dataNascimento}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/pacientes/editarPacientes?id=${paciente.id}">Editar</a>
                    <a href="${pageContext.request.contextPath}/pacientes/deletarPacientes?id=${paciente.id}" onclick="return confirm('Tem certeza que deseja deletar?')">Deletar</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/pacientes/criarPacientes">Adicionar Novo Paciente</a>
    <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
</body>
</html>
