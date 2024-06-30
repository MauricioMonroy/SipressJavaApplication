<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div
        class="modal fade"
        id="registrarHistorialModal"
        tabindex="-1"
        aria-labelledby="registrarHistorialModalLabel"
        aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="registrarHistorialModalLabel">
                    Registro de Historia Clínica
                </h1>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close">
                    <span></span>
                </button>
            </div>
            <form action="${pageContext.request.contextPath}/ServletHistorial?accion=insertar"
                  method="post">
                <div class="modal-body">
                    <div class="col-md-6">
                        <label for="inputNombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="inputNombre" name="nombre">
                    </div>
                    <div class="col-md-6">
                        <label for="inputApellido" class="form-label">Apellido</label>
                        <input type="text" class="form-control" id="inputApellido" name="apellido">
                    </div>
                    <div class="col-md-6">
                        <label for="inputMotivoConsulta" class="form-label">Motivo de Consulta</label>
                        <input type="text" class="form-control" id="inputMotivoConsulta" name="motivoConsulta">
                    </div>
                    <div class="col-md-6">
                        <label for="inputFechaNacimiento" class="form-label">Fecha de Nacimiento</label>
                        <input type="date" class="form-control" id="inputFechaNacimiento" name="fechaNacimiento">
                    </div>
                    <div class="col-md-6">
                        <label for="inputSexo" class="form-label">Sexo</label>
                        <select id="inputSexo" class="form-select" name="sexo">
                            <option selected>Choose...</option>
                            <option value="M">Masculino</option>
                            <option value="F">Femenino</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="inputDireccion" class="form-label">Dirección</label>
                        <input type="text" class="form-control" id="inputDireccion" name="direccion">
                    </div>
                    <div class="col-md-6">
                        <label for="inputOcupacion" class="form-label">Ocupación</label>
                        <input type="text" class="form-control" id="inputOcupacion" name="ocupacion">
                    </div>
                    <div class="col-md-6">
                        <label for="inputContactoEmergencia" class="form-label">Contacto de Emergencia</label>
                        <input type="text" class="form-control" id="inputContactoEmergencia" name="contactoEmergencia">
                    </div>
                    <div class="col-md-6">
                        <label for="inputNombreContactoEmergencia" class="form-label">Nombre del Contacto de
                            Emergencia</label>
                        <input type="text" class="form-control" id="inputNombreContactoEmergencia"
                               name="nombreContactoEmergencia">
                    </div>
                    <div class="col-md-6">
                        <label for="inputAlergias" class="form-label">Alergias</label>
                        <input type="text" class="form-control" id="inputAlergias" name="alergias">
                    </div>
                    <div class="col-md-6">
                        <label for="inputCondicionesPreexistentes" class="form-label">Condiciones Preexistentes</label>
                        <input type="text" class="form-control" id="inputCondicionesPreexistentes"
                               name="condicionesPreexistentes">
                    </div>
                    <div class="col-md-6">
                        <label for="inputMedicamentosActuales" class="form-label">Medicamentos Actuales</label>
                        <input type="text" class="form-control" id="inputMedicamentosActuales" name="medicamentosActuales">
                    </div>
                    <div class="col-md-6">
                        <label for="inputHistorialVacunas" class="form-label">Historial de Vacunas</label>
                        <input type="text" class="form-control" id="inputHistorialVacunas" name="historialVacunas">
                    </div>
                    <div class="col-md-6">
                        <label for="inputGrupoSanguineo" class="form-label">Grupo Sanguíneo</label>
                        <input type="text" class="form-control" id="inputGrupoSanguineo" name="grupoSanguineo">
                    </div>
                    <div class="col-md-12">
                        <label for="inputNotasAdicionales" class="form-label">Notas Adicionales</label>
                        <textarea class="form-control" id="inputNotasAdicionales" name="notasAdicionales"></textarea>
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
                        Guardar
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
