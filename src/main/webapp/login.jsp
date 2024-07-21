<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="page.title" /></title>
    <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1><fmt:message key="page.label" /></h1>
<c:if test="${not empty mensagens}">
    <div id="erro">
        <ul>
            <c:forEach var="mensagem" items="${mensagens.erros}">
                <li>${mensagem}</li>
            </c:forEach>
        </ul>
    </div>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/login">
    <table>
        <tr>
            <th><fmt:message key="user.login" />:</th>
            <td><input type="text" name="login" value="${param.login}"/></td>
        </tr>
        <tr>
            <th><fmt:message key="user.password" />:</th>
            <td><input type="password" name="senha" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" name="bOK" value="<fmt:message key="user.login"/>">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
