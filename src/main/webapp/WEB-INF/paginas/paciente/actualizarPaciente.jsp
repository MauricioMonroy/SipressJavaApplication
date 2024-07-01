<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<!-- Head común a todas las vistas -->
<jsp:include page="/WEB-INF/paginas/comunes/head.jsp"/>

<body>
<!-- Navbar con función responsive -->
<jsp:include page="/WEB-INF/paginas/comunes/navbar.jsp"/>

<!-- Cabecero -->
<jsp:include page="/WEB-INF/paginas/usuario/cabecero.jsp"/>

<!-- Contenido de la página -->
<form action="${pageContext.request.contextPath}/ServletUsuario?accion=modificar&idUsuario=${paciente.idPaciente}"
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
                                <input type="text" class="form-control" id="detalleEps"
                                       name="detalleEps" placeholder="Detalle Eps" required
                                       value="${paciente.detalleEps}"/>
                                <label for="detalleEps">EPS y tipo de vinculación</label>
                            </div>

                            <div class="form-floating form-group mb-3">
                                <input type="text" class="form-control" id="fechaConsulta"
                                       name="fechaConsulta" placeholder="YYYY-MM-DD" pattern="\d{4}-\d{2}-\d{2}"
                                       required
                                       value="${paciente.fechaConsulta}"/>
                                <label for="fechaConsulta">Fecha de consulta (YYYY-MM-DD)</label>
                            </div>

                            <div class="form-floating form-group mb-3">
                                <input type="text" class="form-control" id="nombre"
                                       name="nombre" placeholder="Nombre" required
                                       value="${paciente.usuario.nombre}"/>
                                <label for="nombre">Nombre(s)</label>
                            </div>

                            <div class="form-floating form-group mb-3">
                                <input type="text" class="form-control" id="apellido"
                                       name="apellido" placeholder="Apellido" required
                                       value="${paciente.usuario.apellido}"/>
                                <label for="apellido">Apellido(s)</label>
                            </div>

                            <div class="form-floating form-group mb-3">
                                <input type="text" class="form-control" id="identificacion"
                                       name="identificacion" placeholder="Identificación" required
                                       value="${paciente.usuario.identificacion}"/>
                                <label for="identificacion">Identificación</label>
                            </div>

                            <div class="form-floating form-group mb-3">
                                <input type="tel" class="form-control" id="telefono"
                                       name="telefono" placeholder="telefono" required
                                       value="${paciente.usuario.telefono}"/>
                                <label for="telefono">Teléfono</label>
                            </div>

                            <div class="form-floating form-group mb-3">
                                <input type="email" class="form-control" id="email"
                                       name="email" placeholder="nombre@ejemplo.com" required
                                       value="${paciente.usuario.email}"/>
                                <label for="email">Correo electrónico</label>
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
