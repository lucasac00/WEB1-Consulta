<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Médicos</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css' />" />
</head>
<body>
    <h1>Lista de Médicos</h1>

    <c:choose>
        <c:when test="${not empty listaMedicos}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Email</th>
                        <th>CRM</th>
                        <th>Nome</th>
                        <th>Especialidade</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="medico" items="${listaMedicos}">
                        <tr>
                            <td><c:out value="${medico.email}" /></td>
                            <td><c:out value="${medico.crm}" /></td>
                            <td><c:out value="${medico.nome}" /></td>
                            <td><c:out value="${medico.especialidade}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>Nenhum médico encontrado.</p>
        </c:otherwise>
    </c:choose>

    <a href="<c:url value='/index.jsp' />">Voltar para a página inicial</a>

</body>
</html>
