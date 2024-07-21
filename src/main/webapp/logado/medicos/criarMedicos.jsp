<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Adicionar Médico</title>
</head>
<body>
    <h2>Adicionar Médico</h2>
    <form action="${pageContext.request.contextPath}/medicos/criarMedicos" method="post">
        <label for="email">Email:</label>
        <input type="text" name="email" id="email" required><br>

        <label for="senha">Senha:</label>
        <input type="password" name="senha" id="senha" required><br>

        <label for="crm">CRM:</label>
        <input type="text" name="crm" id="crm" required><br>

        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" required><br>

        <label for="especialidade">Especialidade:</label>
        <input type="text" name="especialidade" id="especialidade" required><br>

        <input type="submit" value="Adicionar">
    </form>
</body>
</html>
