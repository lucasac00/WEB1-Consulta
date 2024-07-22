<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="ListPatientPageTitle" /></title>
</head>
<body>
    <h2><fmt:message key="ListPatientPageTitle" /></h2>
    <table border="1">
        <tr>
            <th><fmt:message key="ListPatientPageID" /></th>
            <th><fmt:message key="ListPatientPageEmail" /></th>
            <th><fmt:message key="ListPatientPageCPF" /></th>
            <th><fmt:message key="ListPatientPageName" /></th>
            <th><fmt:message key="ListPatientPagePhone" /></th>
            <th><fmt:message key="ListPatientPageSex" /></th>
            <th><fmt:message key="ListPatientPageDateOfBirth" /></th>
            <th><fmt:message key="ListPatientPageActions" /></th>
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
                    <a href="${pageContext.request.contextPath}/pacientes/editarPacientes?id=${paciente.id}&ogcpf=${paciente.cpf}"><fmt:message key="ListPatientPageEdit" /></a>
                    <a href="${pageContext.request.contextPath}/pacientes/deletarPacientes?id=${paciente.id}" onclick="return confirm(<fmt:message key='confirmAction' />)"><fmt:message key="ListPatientPageDelete" /></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/pacientes/criarPacientes"><fmt:message key="ListPatientPageAddNewPatient" /></a>
    <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="home" /></a>
</body>
</html>
