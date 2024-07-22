<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="doctorExpertiseList" /></title>
</head>
<body>
    <h2><fmt:message key="doctorsExpertise"/>${especialidade}</h2>
    <table border="1">
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
                    <td>
                        <a href="${pageContext.request.contextPath}/medicos/editarMedicos?id=${medico.id}"><fmt:message key="edit" /></a>
                        <a href="${pageContext.request.contextPath}/medicos/deletarMedicos?id=${medico.id}" onclick="return confirm(<fmt:message key='confirmAction' />)"><fmt:message key='delete' /></a>
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