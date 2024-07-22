<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="listAppointments" /></title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #c0d9ff, #1a73e8);
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
            background: #1a73e8;
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
            background: #1a73e8;
            padding: 10px 20px;
            border-radius: 5px;
            margin: 10px 5px;
            transition: background 0.3s ease;
            display: inline-block;
        }

        a:hover {
            background: #1f15e5;
        }

        .actions a {
            display: block;
            margin-bottom: 10px;
        }

        .actions a.delete {
            background: #e74c3c;
            margin-top: 10px;
        }

        .actions a.delete:hover {
            background: #c0392b;
        }

        .link-container {
            display: flex;
            gap: 20px; /* Ajuste a distância entre os links conforme necessário */
        }
    </style>
</head>
<body>
    <h2><fmt:message key="listAppointments" /></h2>
    <table>
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
    <div class="link-container">
        <a href="${pageContext.request.contextPath}/pacientes/criarConsulta"><fmt:message key="addNewAppointment" /></a>
        <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="home" /></a>
    </div>
</body>
</html>
