<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div
        class="modal fade"
        id="registrarPacienteModal"
        tabindex="-1"
        aria-labelledby="registrarPacienteModalLabel"
        aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content bg-light">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="registrarPacienteModalLabel">
                    Registro de Pacientes
                </h1>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close">
                    <span></span>
                </button>
            </div>
            <form action="${pageContext.request.contextPath}/ServletPaciente?accion=insertar"
                  method="post" class="was-validated">
                <div class="modal-body">
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger">
                                ${errorMessage}
                        </div>
                    </c:if>

                    <div class="form-floating form-group mb-3">
                        <input type="text" class="form-control" id="detalleEps"
                               name="detalleEps" placeholder="Detalle Eps" required/>
                        <label for="detalleEps">EPS y tipo de vinculación</label>
                    </div>

                    <div class="form-floating form-group mb-3">
                        <input type="text" class="form-control" id="fechaConsulta"
                               name="fechaConsulta" placeholder="YYYY-MM-DD" pattern="\d{4}-\d{2}-\d{2}" required/>
                        <label for="fechaConsulta">Fecha de consulta (YYYY-MM-DD)</label>
                    </div>

                    <div class="form-floating form-group mb-3">
                        <input type="text" class="form-control" id="nombre"
                               name="nombre" placeholder="Nombre" required/>
                        <label for="nombre">Nombre(s)</label>
                    </div>

                    <div class="form-floating form-group mb-3">
                        <input type="text" class="form-control" id="apellido"
                               name="apellido" placeholder="Apellido" required/>
                        <label for="apellido">Apellido(s)</label>
                    </div>

                    <div class="form-floating form-group mb-3">
                        <input type="text" class="form-control" id="identificacion"
                               name="identificacion" placeholder="Identificación" required/>
                        <label for="identificacion">Identificación</label>
                    </div>

                    <div class="form-floating form-group mb-3">
                        <input type="tel" class="form-control" id="telefono"
                               name="telefono" placeholder="telefono" required/>
                        <label for="telefono">Teléfono</label>
                    </div>

                    <div class="form-floating form-group mb-3">
                        <input type="email" class="form-control" id="email"
                               name="email" placeholder="nombre@ejemplo.com" required/>
                        <label for="email">Correo electrónico</label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button
                            type="button"
                            class="btn btn-secondary"
                            data-bs-dismiss="modal">
                        Cerrar
                    </button>
                    <button type="submit" class="btn btn-primary">
                        Registrar
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

