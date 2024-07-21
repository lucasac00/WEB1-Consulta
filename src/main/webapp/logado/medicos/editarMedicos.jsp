<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Médico</title>
</head>
<body>
    <h2>Editar Médico</h2>
    <form action="${pageContext.request.contextPath}/medicos/editarMedicos" method="post">
        <input type="hidden" name="id" value="${medico.id}">
        
        <label for="email">Email:</label>
        <input type="text" name="email" id="email" value="${medico.email}" required><br>

        <label for="senha">Senha:</label>
        <input type="password" name="senha" id="senha" value="${medico.senha}" required><br>

        <label for="crm">CRM:</label>
        <input type="text" name="crm" id="crm" value="${medico.crm}" required><br>

        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" value="${medico.nome}" required><br>

        <label for="especialidade">Especialidade:</label>
        <input type="text" name="especialidade" id="especialidade" value="${medico.especialidade}" required><br>

        <input type="submit" value="Salvar">
    </form>
</body>
</html>
