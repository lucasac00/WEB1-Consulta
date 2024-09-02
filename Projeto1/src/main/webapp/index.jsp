<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title><fmt:message key="title" /></title>
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

        h1 {
            color: white;
            margin-bottom: 20px;
        }

        .language-selection {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }

        .language-selection form {
            margin: 0;
        }

        .language-selection input[type="submit"] {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background: #fff;
            color: #333;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.3s ease;
        }

        .language-selection input[type="submit"]:hover {
            background: #ddd;
        }

        .links a {
            display: block;
            padding: 10px 20px;
            margin: 10px 0;
            background: white;
            color: #333;
            text-decoration: none;
            border-radius: 5px;
            text-align: center;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            transition: background 0.3s ease;
        }

        .links a:hover {
            background: #ddd;
        }
    </style>
</head>
<body>
    <h1><fmt:message key="title" /></h1>

    <!-- Contêiner para os botões de seleção de idioma -->
    <div class="language-selection">
        <form action="index.jsp" method="post">
            <input type="hidden" name="lang" value="en">
            <input type="submit" value="English">
        </form>
        <form action="index.jsp" method="post">
            <input type="hidden" name="lang" value="pt">
            <input type="submit" value="Português">
        </form>
    </div>

    <div class="links">
        <c:if test="${usuarioLogado == null}">
            <a href="<c:url value='login.jsp' />"><fmt:message key="login" /></a>
        </c:if>

        <c:if test="${usuarioLogado != null}">
            <a href="<c:url value='logout' />"><fmt:message key="logout" /></a>
        </c:if>

        <a href="<c:url value='medicos/listagemMedicos' />"><fmt:message key="viewDoctors" /></a>
        <a href="<c:url value='medicos/listagemEspecialidades' />"><fmt:message key="viewDoctorsBySpecialty" /></a>

        <c:if test="${usuarioLogado.cargo == 'admin'}">
            <a href="<c:url value='pacientes/listagemPacientes' />"><fmt:message key="viewPatients"/></a>
        </c:if>
        <c:if test="${usuarioLogado.cargo == 'paciente'}">
            <a href="<c:url value='pacientes/listagemConsultas?doc=${usuarioLogado.documento}'/>"><fmt:message key="viewConsultas"></fmt:message></a>
        </c:if>
        <c:if test="${usuarioLogado.cargo == 'paciente'}">
            <a href="<c:url value='pacientes/criarConsulta?doc=${usuarioLogado.documento}'/>"><fmt:message key="addConsultas"></fmt:message></a>
        </c:if>
        <c:if test="${usuarioLogado.cargo == 'medico'}">
            <a href="<c:url value='medicos/listagemConsultas?doc=${usuarioLogado.documento}'/>"><fmt:message key="viewConsultas"></fmt:message></a>
        </c:if>
    </div>
</body>
</html>
