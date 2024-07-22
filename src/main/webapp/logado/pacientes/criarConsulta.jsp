<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="createAppointmentTitle" /></title>
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

        form {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
            display: flex;
            flex-direction: column;
            margin-bottom: 20px;
        }

        label {
            margin-top: 10px;
            color: #333;
            font-weight: bold;
        }

        input[type="text"],
        input[type="datetime-local"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            background: #9b59b6;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.3s ease;
        }

        input[type="submit"]:hover {
            background: #8e44ad;
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
            color: #9b59b6;
            text-decoration: none;
            margin: 0 10px;
        }

        a:hover {
            text-decoration: underline;
        }

        p {
            color: red;
        }
    </style>
    <script>
        window.onload = function() {
            let today = new Date();
            let day = ("0" + today.getDate()).slice(-2);
            let month = ("0" + (today.getMonth() + 1)).slice(-2);
            let year = today.getFullYear();
            let hours = ("0" + today.getHours()).slice(-2);
            let minutes = ("0" + (Math.floor(today.getMinutes() / 30) * 30)).slice(-2);
            let minDateTime = year + "-" + month + "-" + day + "T" + hours + ":" + minutes;
            document.getElementById("data_hora").min = minDateTime;
        }
    </script>
</head>
<body>
    <h2><fmt:message key="createAppointmentTitle" /></h2>
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>
    <form action="${pageContext.request.contextPath}/pacientes/criarConsulta" method="post">
        <label for="cpf"><fmt:message key="cpfPatient" /></label>
        <input type="text" name="cpf" id="cpf" value="${usuarioLogado.documento}" readonly>

        <label for="crm"><fmt:message key="crmDoctor" /></label>
        <input type="text" name="crm" id="crm" required>

        <label for="data_hora"><fmt:message key="createAppointmentDateAndTime" /></label>
        <input type="datetime-local" name="data_hora" id="data_hora" required step="1800">

        <input type="submit" value=<fmt:message key="addAppointment" />>
    </form>
    <table>
        <tr>
            <th><fmt:message key="cid" /></th>
            <th><fmt:message key="cemail" /></th>
            <th><fmt:message key="ccrm" /></th>
            <th><fmt:message key="cname" /></th>
            <th><fmt:message key="cexpertise" /></th>
            <c:if test="${usuarioLogado.cargo == 'medico' || usuarioLogado.cargo == 'admin'}">
                <th><fmt:message key="cactions" /></th>
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
                        <a href="${pageContext.request.contextPath}/medicos/editarMedicos?id=${medico.id}"><fmt:message key="cedit" /></a>
                        <a href="${pageContext.request.contextPath}/medicos/deletarMedicos?id=${medico.id}" onclick="return confirm(<fmt:message key='cconfirm' />)"><fmt:message key="cdelete" /></a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
