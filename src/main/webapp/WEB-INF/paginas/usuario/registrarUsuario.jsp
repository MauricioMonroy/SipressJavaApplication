<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div
        class="modal fade"
        id="registrarUsuarioModal"
        tabindex="-1"
        aria-labelledby="registrarUsuarioModalLabel"
        aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content bg-light">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="registrarUsuarioModalLabel">
                    Registro de Usuarios
                </h1>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close">
                    <span></span>
                </button>
            </div>
            <form action="${pageContext.request.contextPath}/ServletUsuario?accion=insertar"
                  method="post" class="was-validated">
                <div class="modal-body">
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger">
                                ${errorMessage}
                        </div>
                    </c:if>

                    <div class="form-floating form-group mb-3">
                        <input type="text" class="form-control" id="username"
                               name="username" placeholder="Username" required/>
                        <label for="username">Nombre de usuario</label>
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

                    <div class="form-floating form-group mb-3">
                        <input type="password" class="form-control" id="password"
                               name="password" placeholder="Contraseña" required/>
                        <label for="password">Contraseña</label>
                    </div>

                    <div class="form-floating form-group mb-3">
                        <input type="password" class="form-control" id="repeatPassword"
                               name="repeatPassword" placeholder="Contraseña" required/>
                        <label for="repeatPassword">Repita su Contraseña</label>
                    </div>

                    <div class="form-check form-group mb-3">
                        <div>
                            <label class="h5 text-light">
                                ¿Es Paciente?
                                <input type="checkbox" name="esPaciente" value="true" class="custom-checkbox">
                            </label>
                            <label class="h5 text-light">
                                ¿Es Empleado?
                                <input type="checkbox" name="esEmpleado" value="true" class="custom-checkbox">
                            </label>
                        </div>
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

