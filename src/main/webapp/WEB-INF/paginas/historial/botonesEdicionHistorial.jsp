<%@ page contentType="text/html;charset=UTF-8" %>
<section id="actions">
    <div class="container">
        <div class="row justify-content-center">
            <!-- Botón para regresar al Control de Historiales-->
            <div class="col-12 col-md-4 d-flex justify-content-center">
                <a href="ServletHistorial" class="btn btn-info">
                    <i class="fa-solid fa-arrow-left-long"></i> Regresar a la lista
                </a>
            </div>
            <!-- Botón para actualizar el registro seleccionado -->
            <div class="col-12 col-md-4 d-flex justify-content-center">
                <a href="${pageContext.request.contextPath}/ServletHistorial?accion=actualizar&idHistorial=${historial.idHistorial}"
                   class="btn btn-success" type="submit">
                    <i class="fa-solid fa-clipboard-check"></i> Actualizar
                </a>
            </div>
        </div>
    </div>
</section>
