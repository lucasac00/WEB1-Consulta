<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Confirmar Consulta</title>
</head>
<body>
    <h2>Confirmar Consulta</h2>
    <h3>O medico que vc escolheu esta ocupado nesses horarios:</h3>
    <!-- Mostrar os horarios que o meidco esta ocupada mas sem revelar os nomes dos pacientes -->
    <h3>Voce esta ocupado nesses horarios:</h3>
    <!-- Mostrar os horarios das suas outras consultas -->
    <!--<form action="${pageContext.request.contextPath}/pacientes/confirmarConsulta" method="post">
        Fazer um inpit para o horario aqui eu acho

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
        
    </form>-->
</body>
</html>
