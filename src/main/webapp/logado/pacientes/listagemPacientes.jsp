<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="ListPatientPageTitle" /></title>
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
            width: 80%;
            max-width: 800px;
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

        a {
            color: white;
            text-decoration: none;
            background: #9b59b6;
            padding: 10px 20px;
            border-radius: 5px;
            margin: 10px 5px;
            transition: background 0.3s ease;
            display: inline-block;
        }

        a:hover {
            background: #8e44ad;
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
            white-space: nowrap;
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

        .link-container {
            display: flex;
            gap: 20px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h2><fmt:message key="ListPatientPageTitle" /></h2>
    <table>
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
                <td class="actions">
                    <a href="${pageContext.request.contextPath}/pacientes/editarPacientes?id=${paciente.id}&ogcpf=${paciente.cpf}"><fmt:message key="ListPatientPageEdit" /></a>
                    <a href="${pageContext.request.contextPath}/pacientes/deletarPacientes?id=${paciente.id}" class="delete" onclick="return confirm(<fmt:message key='confirmAction' />)"><fmt:message key="ListPatientPageDelete" /></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="link-container">
        <a href="${pageContext.request.contextPath}/pacientes/criarPacientes"><fmt:message key="ListPatientPageAddNewPatient" /></a>
        <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="home" /></a>
    </div>
</body>
</html>
