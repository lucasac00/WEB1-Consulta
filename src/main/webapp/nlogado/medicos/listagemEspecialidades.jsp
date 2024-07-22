<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listagem de Especialidades</title>
</head>
<body>
    <h2>Listagem de de todas as Especialidades Distintas disponíveis</h2>
    <ul>
        <c:forEach var="especialidade" items="${listagemEspecialidades}">
            <li><a href="${pageContext.request.contextPath}/medicos/especialidade?nome=${especialidade}">${especialidade}</a></li>
        </c:forEach>
    </ul>
</body>
</html>