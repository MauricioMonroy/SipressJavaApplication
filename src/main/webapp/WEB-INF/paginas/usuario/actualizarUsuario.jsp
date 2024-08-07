<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<!-- Head común a todas lás vistas -->
<jsp:include page="/WEB-INF/paginas/comunes/head.jsp"/>
<body>
<!-- Navbar con función responsive-->
<jsp:include page="/WEB-INF/paginas/comunes/navbar.jsp"/>
<!--Cabecero-->
<jsp:include page="/WEB-INF/paginas/usuario/cabecero.jsp"/>
<!-- Contenido de la página -->
<form action="${pageContext.request.contextPath}/ServletUsuario?accion=modificar&idUsuario=${usuario.idUsuario}"
      method="post" class="was-validated">
    <!-- Botones de navegación -->
    <jsp:include page="/WEB-INF/paginas/usuario/botonesEdicionUsuarios.jsp"/>
    <!-- Formulario de edición -->
    <section id="details">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-9">
                    <div class="card">
                        <div class="card-header">
                            <h4>Modificar Registro</h4>
                        </div>
                        <div class="card-body">
                            <div class="form-floating form-group mb-3">
                                <input type="text" class="form-control" id="username"
                                       name="username" placeholder="Username" required
                                       value="${usuario.username}"/>
                                <label for="username">Nombre de usuario</label>
                            </div>

                            <div class="form-floating form-group mb-3">
                                <input type="text" class="form-control" id="nombre"
                                       name="nombre" placeholder="Nombre" required
                                       value="${usuario.nombre}"/>
                                <label for="nombre">Nombre(s)</label>
                            </div>

                            <div class="form-floating form-group mb-3">
                                <input type="text" class="form-control" id="apellido"
                                       name="apellido" placeholder="Apellido" required
                                       value="${usuario.apellido}"/>
                                <label for="apellido">Apellido(s)</label>
                            </div>

                            <div class="form-floating form-group mb-3">
                                <input type="text" class="form-control" id="identificacion"
                                       name="identificacion" placeholder="Identificación" required
                                       value="${usuario.identificacion}"/>
                                <label for="identificacion">Identificación</label>
                            </div>

                            <div class="form-floating form-group mb-3">
                                <input type="tel" class="form-control" id="telefono"
                                       name="telefono" placeholder="telefono" required
                                       value="${usuario.telefono}"/>
                                <label for="telefono">Teléfono</label>
                            </div>

                            <div class="form-floating form-group mb-3">
                                <input type="email" class="form-control" id="email"
                                       name="email" placeholder="nombre@ejemplo.com" required
                                       value="${usuario.email}"/>
                                <label for="email">Correo electrónico</label>
                            </div>
                            <div class="form-check form-group mb-3">
                                <div>
                                    <label class="h5">
                                        ¿Es Paciente?
                                        <input type="checkbox" name="esPaciente"
                                               class="custom-checkbox"
                                               <c:if test="${usuario.esPaciente}">checked</c:if>/>
                                    </label>
                                    <label class="h5">
                                        ¿Es Empleado?
                                        <input type="checkbox" name="esEmpleado"
                                               class="custom-checkbox"
                                               <c:if test="${usuario.esEmpleado}">checked</c:if>/>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</form>
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
