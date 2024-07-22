<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Adicionar Paciente</title>
</head>
  <body>
    <h2>Adicionar Paciente</h2>
    <form action="${pageContext.request.contextPath}/pacientes/listagemConsultas" method="post">
      <label for="cpf">CPF do Paciente:</label>
      <input type="text" name="cpf" id="cpf" value="${usuarioLogado.documento}" disabled><br>

      <label for="crm">CRM do Médico:</label>
      <input type="text" name="crm" id="crm" required><br>

      <label for="data_hora">Data e Hora:</label>
      <input type="datetime-local" name="data_hora" id="data_hora" required><br>

      <input type="submit" value="Adicionar">
    </form>
  </body>
</html>
