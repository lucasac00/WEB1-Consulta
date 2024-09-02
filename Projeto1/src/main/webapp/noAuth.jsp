<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="access" /></title>
    <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <h1><fmt:message key="access" /></h1>
    <p><fmt:message key="accessMessage" /></p>
    <p><a href="${pageContext.request.contextPath}/login.jsp"><fmt:message key="accessGoBack" /></a></p>
    <c:if test="${not empty mensagens}">
        <div id="erros">
            <c:forEach var="mensagem" items="${mensagens}">
                <p>${mensagem}</p>
            </c:forEach>
        </div>
    </c:if>
</body>
</html>
