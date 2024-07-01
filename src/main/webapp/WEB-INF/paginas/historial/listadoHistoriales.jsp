<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<section id="historiales">
    <!-- Contenido -->
    <div class="container">
        <div class="row">
            <!-- Lista -->
            <div class="col-md-9">
                <div class="card" id="contenedor-lista">
                    <div class="card-header">
                        <h3 class="text-center">
                            <i class="fa-solid fa-book-medical"></i> Lista de Historiales
                        </h3>
                        <div class="table-responsive">
                            <table class="table table-striped mt-4">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Paciente</th>
                                    <th>Última Actualización</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="historial" items="${historiales}">
                                    <tr>
                                        <td>${historial.idHistorial}</td>
                                        <td>${historial.paciente.usuario.nombre} ${historial.paciente.usuario.apellido}</td>
                                        <td>${historial.ultimaActualizacion}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/ServletHistorial?accion=actualizar&idHistorial=${historial.idHistorial}"
                                               class="btn btn-secondary">
                                                <i class="fa-solid fa-file-pen"></i> Actualizar Historia
                                            </a>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/ServletPaciente?accion=actualizar&idPaciente=${paciente.idPaciente}"
                                               class="btn btn-warning">
                                                <i class="fa-regular fa-pen-to-square"></i> Actualizar Datos
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Consultar número de historiales modal -->
<jsp:include page="/WEB-INF/paginas/historial/cantidadHistoriales.jsp"/>
<!-- Agregar un nuevo registro modal -->
<jsp:include page="/WEB-INF/paginas/historial/registrarHistorial.jsp"/>



