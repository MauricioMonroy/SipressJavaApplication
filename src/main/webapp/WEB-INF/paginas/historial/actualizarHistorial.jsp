<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<!-- Head común a todas las vistas -->
<jsp:include page="/WEB-INF/paginas/comunes/head.jsp"/>

<body>
<!-- Navbar con función responsive -->
<jsp:include page="/WEB-INF/paginas/comunes/navbar.jsp"/>

<!-- Cabecero -->
<jsp:include page="/WEB-INF/paginas/historial/cabecero.jsp"/>

<!-- Contenido de la página -->
<form action="${pageContext.request.contextPath}/ServletHistorial?accion=modificar&idHistorial=${historial.idHistorial}"
      method="post">
    <!-- Botones de navegación -->
    <jsp:include page="/WEB-INF/paginas/historial/botonesEdicionHistorial.jsp"/>
    <!-- Formulario de edición -->
    <section id="details">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-9">
                    <div class="card">
                        <div class="card-header">
                            <h4>Modificar Historial</h4>
                        </div>
                        <div class="card-body">
                            <div class="row g-3">
                                <div class="col-md-6">
                                    <label for="inputNombre" class="form-label">Nombre</label>
                                    <input type="text" class="form-control" id="inputNombre" name="nombre"
                                           value="${historial.paciente.usuario.nombre}" disabled readonly/>
                                </div>
                                <div class="col-md-6">
                                    <label for="inputApellido" class="form-label">Apellido</label>
                                    <input type="text" class="form-control" id="inputApellido" name="apellido"
                                           value="${historial.paciente.usuario.apellido}" disabled readonly/>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputIdentificacion" class="form-label">Identificación</label>
                                    <input type="text" class="form-control" id="inputIdentificacion"
                                           name="identificacion"
                                           value="${historial.paciente.usuario.identificacion}" disabled readonly/>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputTelefono" class="form-label">Teléfono</label>
                                    <input type="text" class="form-control" id="inputTelefono" name="telefono"
                                           value="${historial.paciente.usuario.telefono}" disabled readonly/>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputEmail" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="inputEmail" name="email"
                                           value="${historial.paciente.usuario.email}" disabled readonly/>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputFechaNacimiento" class="form-label">Fecha de Nacimiento</label>
                                    <input type="date" class="form-control" id="inputFechaNacimiento"
                                           name="fechaNacimiento"
                                           value="${historial.fechaNacimiento}"/>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputDetalleEps" class="form-label">Detalle EPS</label>
                                    <input type="text" class="form-control" id="inputDetalleEps" name="detalleEps"
                                           value="${historial.paciente.detalleEps}"/>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputMotivoConsulta" class="form-label">Motivo de Consulta</label>
                                    <textarea class="form-control" id="inputMotivoConsulta"
                                              name="motivoConsulta"></textarea>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputSexo" class="form-label">Sexo</label>
                                    <select id="inputSexo" class="form-select" name="sexo">
                                        <option selected value="${historial.sexo}">Choose...</option>
                                        <option value="M">Masculino</option>
                                        <option value="F">Femenino</option>
                                    </select>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputDireccion" class="form-label">Dirección</label>
                                    <input type="text" class="form-control" id="inputDireccion" name="direccion"
                                           value="${historial.direccion}"/>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputOcupacion" class="form-label">Ocupación</label>
                                    <input type="text" class="form-control" id="inputOcupacion" name="ocupacion"
                                           value="${historial.ocupacion}"/>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputContactoEmergencia" class="form-label">Contacto de
                                        Emergencia</label>
                                    <input type="text" class="form-control" id="inputContactoEmergencia"
                                           name="contactoEmergencia" value="${historial.contactoEmergencia}">
                                </div>
                                <div class="col-md-4">
                                    <label for="inputNombreContactoEmergencia" class="form-label">Nombre del Contacto de
                                        Emergencia</label>
                                    <input type="text" class="form-control" id="inputNombreContactoEmergencia"
                                           name="nombreContactoEmergencia"
                                           value="${historial.nombreContactoEmergencia}"/>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputGrupoSanguineo" class="form-label">Grupo Sanguíneo</label>
                                    <select id="inputGrupoSanguineo" class="form-select" name="grupoSanguineo">
                                        <option selected value="${historial.grupoSanguineo}">Choose...</option>
                                        <option value="A+">A+</option>
                                        <option value="A-">A-</option>
                                        <option value="B+">B+</option>
                                        <option value="B-">B-</option>
                                        <option value="AB+">AB+</option>
                                        <option value="AB-">AB-</option>
                                        <option value="O+">O+</option>
                                        <option value="O-">O-</option>
                                    </select>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputAlergias" class="form-label">Alergias</label>
                                    <textarea class="form-control" id="inputAlergias" name="alergias"></textarea>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputCondicionesPreexistentes" class="form-label">Condiciones
                                        Preexistentes</label>
                                    <textarea class="form-control" id="inputCondicionesPreexistentes"
                                              name="condicionesPreexistentes"></textarea>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputMedicamentosActuales" class="form-label">Medicamentos
                                        Actuales</label>
                                    <textarea class="form-control" id="inputMedicamentosActuales"
                                              name="medicamentosActuales"></textarea>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputHistorialVacunas" class="form-label">Historial de Vacunas</label>
                                    <textarea class="form-control" id="inputHistorialVacunas"
                                              name="historialVacunas"></textarea>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputNotasAdicionales" class="form-label">Notas Adicionales</label>
                                    <textarea class="form-control" id="inputNotasAdicionales"
                                              name="notasAdicionales"></textarea>
                                </div>
                                <div class="col-md-4">
                                    <label for="inputArchivosAdjuntos" class="form-label">Adjuntar archivo...</label>
                                    <input type="file" class="form-control" id="inputArchivosAdjuntos"
                                           name="archivosAdjuntos">
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