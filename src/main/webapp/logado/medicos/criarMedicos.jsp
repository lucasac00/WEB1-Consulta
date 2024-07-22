<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="AddDoctor" /></title>
</head>
<body>
    <h2><fmt:message key="AddDoctor" /></h2>
    <form action="${pageContext.request.contextPath}/medicos/criarMedicos" method="post">
        <label for="email"><fmt:message key="email" /></label>
        <input type="text" name="email" id="email" required><br>

        <label for="senha"><fmt:message key="password" /></label>
        <input type="password" name="senha" id="senha" required><br>

        <label for="crm"><fmt:message key="crm" /></label>
        <input type="text" name="crm" id="crm" required><br>

        <label for="nome"><fmt:message key="name" /></label>
        <input type="text" name="nome" id="nome" required><br>

        <label for="especialidade"><fmt:message key="expertise" />:</label>
        <input type="text" name="especialidade" id="especialidade" required><br>

        <input type="submit" value="Adicionar">
    </form>
</body>
</html>
