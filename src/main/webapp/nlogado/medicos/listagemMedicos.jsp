<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="doctorsList" /></title>
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
            padding: 10px;
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
    </style>
    <script>
        // Função para obter o valor de um cookie pelo nome
        function getCookie(name) {
            let matches = document.cookie.match(new RegExp(
                "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
            ));
            return matches ? decodeURIComponent(matches[1]) : undefined;
        }

        // Verifica se existe um cookie de idioma e redireciona a página se necessário
        window.onload = function() {
            let lang = getCookie("lang");
            if (lang && !window.location.search.includes("lang=" + lang)) {
                let newUrl = window.location.pathname + "?lang=" + lang;
                window.location.href = newUrl;
            }
        }
    </script>
</head>
<body>
    <h2><fmt:message key="doctorsList" /></h2>
    <table>
        <tr>
            <th><fmt:message key="lid" /></th>
            <th><fmt:message key="lemail" /></th>
            <th><fmt:message key="lcrm" /></th>
            <th><fmt:message key="lname" /></th>
            <th><fmt:message key="lexpertise" /></th>
            <c:if test="${usuarioLogado.cargo == 'medico' || usuarioLogado.cargo == 'admin'}">
                <th><fmt:message key="lactions" /></th>
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
                        <a href="${pageContext.request.contextPath}/medicos/editarMedicos?id=${medico.id}" class="edit"><fmt:message key="editDoctor" /></a>
                        <a href="${pageContext.request.contextPath}/medicos/deletarMedicos?id=${medico.id}" class="delete" onclick="return confirm(<fmt:message key='confirmAction' />)"><fmt:message key="delete" /></a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${usuarioLogado.cargo == 'medico' || usuarioLogado.cargo == 'admin'}">
        <a href="${pageContext.request.contextPath}/medicos/criarMedicos" class="button"><fmt:message key="addDoctor" /></a>
    </c:if>
    <a href="${pageContext.request.contextPath}/index.jsp" class="button"><fmt:message key="home" /></a>
</body>
</html>
