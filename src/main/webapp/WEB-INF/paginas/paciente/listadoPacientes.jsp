<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<section id="pacientes">
    <!-- Contenido -->
    <div class="container">
        <div class="row">
            <!-- Lista -->
            <div class="col-md-9">
                <div class="card" id="contenedor-lista">
                    <div class="card-header">
                        <h3 class="text-center">
                            <i class="fa-solid fa-clipboard-list"></i> Lista de Pacientes
                        </h3>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nombre y Apellido</th>
                                <th>Detalle EPS</th>
                                <th>Identificación</th>
                                <th>Última Consulta</th>
                                <th>Teléfono</th>
                                <th>Email</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="paciente" items="${sessionScope.pacientes}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${paciente.usuario.nombre} ${paciente.usuario.apellido}</td>
                                    <td>${paciente.detalleEps}</td>
                                    <td>${paciente.usuario.identificacion}</td>
                                    <td>${paciente.fechaConsulta}</td>
                                    <td>${paciente.usuario.telefono}</td>
                                    <td>${paciente.usuario.email}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletPaciente?accion=actualizar&idPaciente=${paciente.idPaciente}"
                                           class="btn btn-secondary">
                                            <i class="fa-regular fa-pen-to-square"></i> Actualizar
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
</section>
<!-- Consultar número de pacientes modal -->
<jsp:include page="/WEB-INF/paginas/paciente/cantidadPacientes.jsp"/>
<!-- Agregar un nuevo registro modal -->
<jsp:include page="/WEB-INF/paginas/paciente/registrarPaciente.jsp"/>

