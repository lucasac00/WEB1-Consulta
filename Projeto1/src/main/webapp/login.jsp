<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="page.title" /></title>
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
            font-size: 2em;
        }

        form {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }

        table {
            width: 100%;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            color: #333;
        }

        td {
            color: #555;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
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

        #erro {
            background: #ffdddd;
            border-left: 6px solid #f44336;
            margin-bottom: 15px;
            padding: 10px;
        }

        #erro ul {
            margin: 0;
            padding: 0;
            list-style-type: none;
        }

        a {
            color: white;
            text-decoration: none;
            margin-top: 20px;
            display: inline-block;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
    <script>
        // Função para obter o valor de um cookie pelo nome
        function getCookie(name) {
            let matches = document.cookie.match(new RegExp(
                "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
            ));
            return matches ? decodeURIComponent(matches[1]) : undefined;
        }

        // Verifica se existe um cookie de idioma e redireciona a página se necessário
        window.onload = function() {
            let lang = getCookie("lang");
            if (lang && !window.location.search.includes("lang=" + lang)) {
                let newUrl = window.location.pathname + "?lang=" + lang;
                window.location.href = newUrl;
            }
        }
    </script>
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
                    <input type="submit" name="bOK" value="<fmt:message key='user.loginButton'/>">
                </td>
            </tr>
        </table>
    </form>
    <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="home" /></a>
</body>
</html>
