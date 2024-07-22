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
  </body>
</html>
