<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<!-- Head común a todas lás vistas -->
<jsp:include page="WEB-INF/paginas/comunes/head.jsp"/>

<body>
<!-- Navbar con función responsive -->
<jsp:include page="WEB-INF/paginas/comunes/navbar.jsp"/>

<!--Cabecero-->
<jsp:include page="WEB-INF/paginas/paciente/cabecero.jsp"/>

<!-- Botones de navegación -->
<jsp:include page="WEB-INF/paginas/paciente/botonesNavegacionPacientes.jsp"/>

<!-- Contenido de la página -->
<jsp:include page="WEB-INF/paginas/paciente/listadoPacientes.jsp"/>

<!-- Pie de página -->
<jsp:include page="WEB-INF/paginas/comunes/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Enlace a Bootstrap JS -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
