<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title><fmt:message key="title" /></title>
    <style>
        .language-selection {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }
        .language-selection form {
            margin: 0;
        }
    </style>
</head>
<body>
    <h1><fmt:message key="title" /></h1>

    <!-- Contêiner para os botões de seleção de idioma -->
    <div class="language-selection">
        <form action="" method="get">
            <input type="hidden" name="lang" value="en">
            <input type="submit" value="English" onclick="setLanguage('en')">
        </form>
        <form action="" method="get">
            <input type="hidden" name="lang" value="pt">
            <input type="submit" value="Português" onclick="setLanguage('pt')">
        </form>
    </div>

    <c:if test="${usuarioLogado == null}">
        <a href="<c:url value='login' />"><fmt:message key="login" /></a>
    </c:if>
    <a href="<c:url value='medicos/listagemMedicos' />"><fmt:message key="viewDoctors" /></a>
    <a href="<c:url value='medicos/listagemEspecialidades' />"><fmt:message key="viewDoctorsBySpecialty" /></a>
    <br></br>
    <c:if test="${usuarioLogado.cargo == 'admin'}">
        <a href="<c:url value='pacientes/listagemPacientes' />"><fmt:message key="viewPatients"/></a>
    </c:if>
    <c:if test="${usuarioLogado.cargo == 'paciente'}">
        <a href="<c:url value='pacientes/listagemConsultas?doc=${usuarioLogado.documento}'/>"><fmt:message key="viewConsultas"></fmt:message></a>
    </c:if>

    <script>
        function setLanguage(lang) {
            document.cookie = "lang=" + lang + "; path=/";
        }
    </script>
</body>
</html>
