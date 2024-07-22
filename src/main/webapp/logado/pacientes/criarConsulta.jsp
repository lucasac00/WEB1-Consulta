<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <title><fmt:message key="createAppointmentTitle" /></title>
  </head>
  <script>
    window.onload = function() {
      let today = new Date();
      let day = ("0" + today.getDate()).slice(-2);
      let month = ("0" + (today.getMonth() + 1)).slice(-2);
      let year = today.getFullYear();
      let hours = ("0" + today.getHours()).slice(-2);
      let minutes = ("0" + (Math.floor(today.getMinutes() / 30) * 30)).slice(-2);
      let minDateTime = year + "-" + month + "-" + day + "T" + hours + ":" + minutes;
      document.getElementById("data_hora").min = minDateTime;
    }
  </script>

  <body>
    <h2><fmt:message key="addPatient" /></h2>
    <c:if test="${not empty errorMessage}">
      <p style="color: red;">${errorMessage}</p>
    </c:if>
    <form action="${pageContext.request.contextPath}/pacientes/criarConsulta" method="post">
      <label for="cpf"><fmt:message key="cpfPatient" /></label>
      <input type="text" name="cpf" id="cpf" value="${usuarioLogado.documento}" readonly><br>

      <label for="crm"><fmt:message key="crmDoctor" /></label>
      <input type="text" name="crm" id="crm" required><br>

      <label for="data_hora"><fmt:message key="createAppointmentDateAndTime" /></label>
      <input type="datetime-local" name="data_hora" id="data_hora" required step="1800"><br>

      <input type="submit" value="Adicionar">
    </form>
    <table border="1">
      <tr>
        <th>ID</th>
        <th>Email</th>
        <th>CRM</th>
        <th>Nome</th>
        <th>Especialidade</th>
        <c:if test="${usuarioLogado.cargo == 'medico' || usuarioLogado.cargo == 'admin'}">
          <th>Ações</th>
        </c:if>
      </tr>
      <c:forEach var="medico" items="${listaMedicos}">
        <tr>
          <td>${medico.id}</td>
          <td>${medico.email}</td>
          <td>${medico.crm}</td>
          <td>${medico.nome}</td>
          <td>${medico.especialidade}</td>
          <c:if test="${usuarioLogado.cargo == 'medico' || usuarioLogado.cargo == 'admin'}">
            <td>
              <a href="${pageContext.request.contextPath}/medicos/editarMedicos?id=${medico.id}">Editar</a>
              <a href="${pageContext.request.contextPath}/medicos/deletarMedicos?id=${medico.id}" onclick="return confirm('Tem certeza que deseja deletar?')">Deletar</a>
            </td>
          </c:if>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
