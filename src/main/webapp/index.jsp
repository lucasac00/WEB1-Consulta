<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Test Page</title>
</head>
<body>
    <h1>Test Page</h1>
    <a href="<c:url value='medicos/listagemMedicos' />">Ver Lista de Médicos</a>
    <br></br>
    <a href="<c:url value='login' />">Login</a>
    <br></br>
    <a href="<c:url value='pacientes/agendarConsulta' />">agendarConsulta</a>
</body>
</html>
