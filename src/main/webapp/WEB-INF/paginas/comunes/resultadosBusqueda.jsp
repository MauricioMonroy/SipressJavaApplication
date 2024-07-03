<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<!-- Head común a todas lás vistas -->
<jsp:include page="/WEB-INF/paginas/comunes/head.jsp"/>
<body>
<!-- Navbar con función responsive -->
<jsp:include page="/WEB-INF/paginas/comunes/navbar.jsp"/>
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
<!-- Pie de página -->
<jsp:include page="/WEB-INF/paginas/comunes/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Enlace a Bootstrap JS -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>

