<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section id="actions">
    <div class="container">
        <div class="row justify-content-center">
            <!-- Botón para regresar al Control de Empleados-->
            <div class="col-12 col-md-4 d-flex justify-content-center">
                <a href="ServletEmpleado" class="btn btn-info">
                    <i class="fa-solid fa-arrow-left-long"></i> Regresar a la lista
                </a>
            </div>
            <!-- Botón para guardar el registro seleccionado -->
            <div class="col-12 col-md-4 d-flex justify-content-center">
                <button class="btn btn-success" type="submit">
                    <i class="fa-solid fa-clipboard-check"></i> Guardar Cambios
                </button>
            </div>
            <!-- Botón para eliminar registro seleccionado -->
            <div class="col-12 col-md-4 d-flex justify-content-center">
                <a href="${pageContext.request.contextPath}/ServletEmpleado?accion=eliminar&idEmpleado=${empleado.idEmpleado}"
                   class="btn btn-danger" type="submit">
                    <i class="fa-solid fa-user-slash"></i> Eliminar Registro
                </a>
            </div>
        </div>
    </div>
</section>

