<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listagem de Consultas</title>
</head>
<body>
<h2>Listagem de Consultas</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>CPF</th>
        <th>CRM</th>
        <th>Data e Hora</th>
    </tr>
    <c:forEach var="consulta" items="${listaConsultas}">
        <tr>
            <td>${consulta.id}</td>
            <td>${consulta.cpfPaciente}</td>
            <td>${consulta.crmMedico}</td>
            <td>${consulta.dataHora}</td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/pacientes/criarPacientes">Adicionar Novo Paciente</a>
<a href="${pageContext.request.contextPath}/index.jsp">Home</a>
</body>
</html>
