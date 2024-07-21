<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listagem de Médicos</title>
</head>
<body>
    <h2>Listagem de Médicos</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Email</th>
            <th>CRM</th>
            <th>Nome</th>
            <th>Especialidade</th>
            <c:if test="${usuarioLogado.cargo == 'medico' || usuarioLogado.cargo == 'admin'}">
                <th>Ações</th>
            </c:if>
        </tr>
        <c:forEach var="medico" items="${listaMedicos}">
            <tr>
                <td>${medico.id}</td>
                <td>${medico.email}</td>
                <td>${medico.crm}</td>
                <td>${medico.nome}</td>
                <td>${medico.especialidade}</td>
                <c:if test="${usuarioLogado.cargo == 'medico' || usuarioLogado.cargo == 'admin'}">
                    <td>
                        <a href="${pageContext.request.contextPath}/medicos/editarMedicos?id=${medico.id}">Editar</a>
                        <a href="${pageContext.request.contextPath}/medicos/deletarMedicos?id=${medico.id}" onclick="return confirm('Tem certeza que deseja deletar?')">Deletar</a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${usuarioLogado.cargo == 'medico' || usuarioLogado.cargo == 'admin'}">
        <a href="${pageContext.request.contextPath}/medicos/criarMedicos">Adicionar Novo Médico</a>
    </c:if>
</body>
</html>
