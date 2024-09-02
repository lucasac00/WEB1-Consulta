<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="expertiseList" /></title>
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

        ul {
            list-style-type: none;
            padding: 0;
            width: 100%;
            max-width: 400px;
        }

        li {
            background: white;
            margin: 10px 0;
            padding: 15px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            transition: background 0.3s ease;
        }

        li:hover {
            background: #f0f0f0;
        }

        a {
            color: #9b59b6;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }

        .nav-links {
            display: flex;
            gap: 20px;
            margin-top: 20px;
        }

        .nav-links a {
            color: white;
            text-decoration: none;
            background: #9b59b6;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background 0.3s ease;
        }

        .nav-links a:hover {
            background: #8e44ad;
        }
    </style>
</head>
<body>
    <h2><fmt:message key="expertiseList" /></h2>
    <ul>
        <c:forEach var="especialidade" items="${listagemEspecialidades}">
            <li><a href="${pageContext.request.contextPath}/medicos/especialidade?nome=${especialidade}">${especialidade}</a></li>
        </c:forEach>
    </ul>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="home" /></a>
    </div>
</body>
</html>