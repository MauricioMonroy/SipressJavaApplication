<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<section id="empleados">
    <!-- Contenido -->
    <div class="container">
        <div class="row">
            <!-- Lista -->
            <div class="col-md-9">
                <div class="card" id="contenedor-lista">
                    <div class="card-header">
                        <h3 class="text-center">
                            <i class="fa-regular fa-id-card"></i> Lista de Empleados
                        </h3>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nombre y Apellido</th>
                                <th>Identificación</th>
                                <th>Cargo</th>
                                <th>Teléfono</th>
                                <th>Email</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="empleado" items="${sessionScope.empleados}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${empleado.usuario.nombre} ${empleado.usuario.apellido}</td>
                                    <td>${empleado.usuario.identificacion}</td>
                                    <td>${empleado.cargo}</td>
                                    <td>${empleado.usuario.telefono}</td>
                                    <td>${empleado.usuario.email}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletEmpleado?accion=actualizar&idEmpleado=${empleado.idEmpleado}"
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
<!-- Consultar número de empleados modal -->
<jsp:include page="/WEB-INF/paginas/empleado/cantidadEmpleados.jsp"/>
<!-- Agregar un nuevo registro modal -->
<jsp:include page="/WEB-INF/paginas/empleado/registrarEmpleado.jsp"/>

