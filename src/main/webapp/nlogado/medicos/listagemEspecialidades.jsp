<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="expertiseList" /></title>
</head>
<body>
    <h2><fmt:message key="expertiseList" /></h2>
    <ul>
        <c:forEach var="especialidade" items="${listagemEspecialidades}">
            <li><a href="${pageContext.request.contextPath}/medicos/especialidade?nome=${especialidade}">${especialidade}</a></li>
        </c:forEach>
    </ul>
    <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="home" /></a>
</body>
</html>