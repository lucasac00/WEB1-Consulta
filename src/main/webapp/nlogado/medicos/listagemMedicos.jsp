<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="doctorsList" /></title>
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
    <table border="1">
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
                    <td>
                        <a href="${pageContext.request.contextPath}/medicos/editarMedicos?id=${medico.id}&ogcrm=${medico.crm}"><fmt:message key="editDoctor" /></a>
                        <a href="${pageContext.request.contextPath}/medicos/deletarMedicos?id=${medico.id}" onclick="return confirm(<fmt:message key='confirmAction' />)"><fmt:message key="delete" /></a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${usuarioLogado.cargo == 'medico' || usuarioLogado.cargo == 'admin'}">
        <a href="${pageContext.request.contextPath}/medicos/criarMedicos"><fmt:message key="addDoctor" /></a>
    </c:if>
    <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="home" /></a>
</body>
</html>
