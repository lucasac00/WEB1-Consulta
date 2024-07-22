<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="listAppointments" /></title>
</head>
<body>
<h2><fmt:message key="listAppointments" /></h2>
<table border="1">
    <tr>
        <th><fmt:message key="listAppointmentID" /></th>
        <th><fmt:message key="listAppointmentCPF" /></th>
        <th><fmt:message key="listAppointmentCRM" /></th>
        <th><fmt:message key="listDateAndTime" /></th>
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
<a href="${pageContext.request.contextPath}/pacientes/criarConsulta"><fmt:message key="addNewAppointment" /></a>
<a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="home" /></a>
</body>
</html>
