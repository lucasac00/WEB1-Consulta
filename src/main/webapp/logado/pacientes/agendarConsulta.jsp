<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Agendamento de Consulta</title>
</head>
<body>
    <h2>Agendar Consulta</h2>
    <form action="${pageContext.request.contextPath}/pacientes/insercao" method="post">
        <label for="crm_medico">Escolha o Médico:</label>
        <select id="crm_medico" name="crm_medico">
            <c:forEach var="medico" items="${listaMedicos}">
                <option value="${medico.crm}">${medico.nome} - ${medico.especialidade}</option>
            </c:forEach>
        </select>
        <br/>
        <label for="data_hora">Data e Hora:</label>
        <input type="datetime-local" id="data_hora" name="data_hora" required>
        <br/>
        <input type="hidden" name="cpf_paciente" value="${usuarioLogado.cpf}">
        <input type="submit" value="Agendar">
    </form>
</body>
</html>
