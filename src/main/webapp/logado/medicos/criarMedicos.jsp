<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="addDoctor" /></title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #c0d9ff, #1a73e8);
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

        form {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
            display: flex;
            flex-direction: column;
        }

        label {
            margin-top: 10px;
            color: #333;
            font-weight: bold;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            background: #1a73e8;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.3s ease;
        }

        input[type="submit"]:hover {
            background: #1f15e5;
        }
    </style>
</head>
<body>
    <h2><fmt:message key="addDoctor" /></h2>
    <form action="${pageContext.request.contextPath}/medicos/criarMedicos" method="post">
        <label for="email"><fmt:message key="email" /></label>
        <input type="text" name="email" id="email" required>

        <label for="senha"><fmt:message key="password" /></label>
        <input type="password" name="senha" id="senha" required>

        <label for="crm"><fmt:message key="crm" /></label>
        <input type="text" name="crm" id="crm" required>

        <label for="nome"><fmt:message key="name" /></label>
        <input type="text" name="nome" id="nome" required>

        <label for="especialidade"><fmt:message key="expertise" /></label>
        <input type="text" name="especialidade" id="especialidade" required>

        <input type="submit" value=<fmt:message key="ladd" />>
    </form>
</body>
</html>
