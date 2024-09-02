<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="doctorExpertiseList" /></title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #a8c0ff, #3f2b96);
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

        table {
            width: 90%; /* Aumentar o tamanho da tabela */
            max-width: 1000px;
            border-collapse: collapse;
            margin-bottom: 20px;
            background: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 15px;
            text-align: left;
        }

        th {
            background: #9b59b6;
            color: white;
            font-weight: bold;
        }

        td {
            border-bottom: 1px solid #ddd;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .actions {
            display: flex;
            gap: 10px;
        }

        .actions a {
            color: white;
            text-decoration: none;
            background: #9b59b6;
            padding: 8px 16px;
            border-radius: 5px;
            transition: background 0.3s ease;
            white-space: nowrap; /* Impede a quebra de linha */
        }

        .actions a.edit {
            background: #9b59b6;
        }

        .actions a.delete {
            background: #e74c3c;
        }

        .actions a:hover {
            background: #8e44ad;
        }

        .actions a.delete:hover {
            background: #c0392b;
        }

        a.button {
            color: white;
            text-decoration: none;
            background: #9b59b6;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background 0.3s ease;
            margin: 10px 5px;
            display: inline-block;
        }

        a.button:hover {
            background: #8e44ad;
        }

        .link-container {
            display: flex;
            gap: 20px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h2><fmt:message key="doctorsExpertise"/>${especialidade}</h2>
    <table>
        <tr>
            <th><fmt:message key="doctorExpertiseID" /></th>
            <th><fmt:message key="doctorExpertiseEmail" /></th>
            <th><fmt:message key="doctorExpertiseCRM" /></th>
            <th><fmt:message key="doctorExpertiseName" /></th>
            <th><fmt:message key="doctorExpertiseExpertise" /></th>
            <c:if test="${usuarioLogado.cargo == 'medico' || usuarioLogado.cargo == 'admin'}">
                <th><fmt:message key="actions" /></th>
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
                    <td class="actions">
                        <a href="${pageContext.request.contextPath}/medicos/editarMedicos?id=${medico.id}" class="edit"><fmt:message key="edit" /></a>
                        <a href="${pageContext.request.contextPath}/medicos/deletarMedicos?id=${medico.id}" class="delete" onclick="return confirm(<fmt:message key='confirmAction' />)"><fmt:message key='delete' /></a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${usuarioLogado.cargo == 'medico' || usuarioLogado.cargo == 'admin'}">
        <a href="${pageContext.request.contextPath}/medicos/criarMedicos" class="button"><fmt:message key="addDoctor" /></a>
    </c:if>
    <div class="link-container">
        <a href="${pageContext.request.contextPath}/index.jsp" class="button"><fmt:message key="home" /></a>
    </div>
</body>
</html>
