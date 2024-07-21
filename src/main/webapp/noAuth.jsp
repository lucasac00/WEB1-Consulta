<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Acesso Negado</title>
    <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <h1>Acesso Negado</h1>
    <p>Você não tem permissão para acessar esta página.</p>
    <p><a href="${pageContext.request.contextPath}/login.jsp">Voltar para a página de login</a></p>
    <c:if test="${not empty mensagens}">
        <div id="erros">
            <c:forEach var="mensagem" items="${mensagens}">
                <p>${mensagem}</p>
            </c:forEach>
        </div>
    </c:if>
</body>
</html>
