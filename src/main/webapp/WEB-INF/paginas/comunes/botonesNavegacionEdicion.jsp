<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section id="actions">
    <div class="container">
        <div class="row">
            <!-- Botón para regresar al Control de Usuarios-->
            <div class="col-md-3">
                <a href="ServletControlador" class="btn btn-info btn-block">
                    <i class="fa-regular fa-square-caret-left"></i> Regresar a la lista
                </a>
            </div>
            <!-- Botón para guardar el registro seleccionado -->
            <div class="col-md-3">
                <button class="btn btn-success btn-block" type="submit">
                    <i class="fa-solid fa-clipboard-check"></i> Guardar Cambios
                </button>
            </div>
            <!-- Botón para eliminar registro seleccionado -->
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminar&idUsuario=${usuario.idUsuario}"
                   class="btn btn-danger btn-block" type="submit">
                    <i class="fa-solid fa-user-slash"></i> Eliminar Registro
                </a>
            </div>
        </div>
    </div>
</section>
