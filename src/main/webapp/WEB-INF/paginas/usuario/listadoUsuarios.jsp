<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<section id="usuarios">
    <!-- Contenido -->
    <div class="container">
        <div class="row">
            <!-- Lista -->
            <div class="col-md-9">
                <div class="card" id="contenedor-lista">
                    <div class="card-header">
                        <h3 class="text-center">
                            <i class="fa-regular fa-rectangle-list"></i> Lista de Usuarios
                        </h3>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Usuario</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Identificación</th>
                                <th>Teléfono</th>
                                <th>Email</th>
                                <th>Tipo de usuario</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="usuario" items="${usuarios}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${usuario.username}</td>
                                    <td>${usuario.nombre}</td>
                                    <td>${usuario.apellido}</td>
                                    <td>${usuario.identificacion}</td>
                                    <td>${usuario.telefono}</td>
                                    <td>${usuario.email}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${usuario.esPaciente && usuario.esEmpleado}">
                                                Paciente y Empleado
                                            </c:when>
                                            <c:when test="${usuario.esPaciente}">
                                                Paciente
                                            </c:when>
                                            <c:when test="${usuario.esEmpleado}">
                                                Empleado
                                            </c:when>
                                            <c:otherwise>
                                                N/A
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=actualizar&idUsuario=${usuario.idUsuario}"
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
<!-- Consultar número de usuarios modal -->
<jsp:include page="/WEB-INF/paginas/usuario/cantidadUsuarios.jsp"/>
<!-- Agregar un nuevo registro modal -->
<jsp:include page="/WEB-INF/paginas/usuario/registrarUsuario.jsp"/>
