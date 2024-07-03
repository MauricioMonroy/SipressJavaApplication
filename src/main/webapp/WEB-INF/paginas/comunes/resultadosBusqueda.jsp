<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Resultados de Búsqueda</title>
    <link rel="stylesheet" href="path/to/bootstrap.css">
</head>
<body>
<div class="container">
    <h1>Resultados de Búsqueda</h1>

    <h2>Usuarios</h2>
    <c:if test="${!empty resultadosUsuarios}">
        <ul>
            <c:forEach var="usuario" items="${resultadosUsuarios}">
                <li>${usuario.username} - ${usuario.nombre} ${usuario.apellido}</li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty resultadosUsuarios}">
        <p>No se encontraron usuarios.</p>
    </c:if>

    <h2>Pacientes</h2>
    <c:if test="${!empty resultadosPacientes}">
        <ul>
            <c:forEach var="paciente" items="${resultadosPacientes}">
                <li>${paciente.nombre} ${paciente.apellido}</li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty resultadosPacientes}">
        <p>No se encontraron pacientes.</p>
    </c:if>

    <h2>Empleados</h2>
    <c:if test="${!empty resultadosEmpleados}">
        <ul>
            <c:forEach var="empleado" items="${resultadosEmpleados}">
                <li>${empleado.nombre} ${empleado.apellido}</li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty resultadosEmpleados}">
        <p>No se encontraron empleados.</p>
    </c:if>

</div>
</body>
</html>

