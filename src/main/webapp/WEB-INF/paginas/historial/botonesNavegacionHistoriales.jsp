<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section id="actions">
    <div class="container">
        <div class="row">
            <!-- Botón para mostrar el número de historiales -->
            <div class="col-12 col-md-4 d-flex justify-content-center">
                <a href="#" class="btn btn-info"
                   data-bs-toggle="modal" data-bs-target="#cantidadHistorialesModal">
                    <i class="fa-solid fa-clipboard-question"></i> Total Registros
                </a>
            </div>
            <!-- Botón para agregar un nuevo registro -->
            <div class="col-12 col-md-4 d-flex justify-content-center">
                <a href="#" class="btn btn-success"
                   data-bs-toggle="modal" data-bs-target="#registrarHistorialModal">
                    <i class="fa-solid fa-notes-medical"></i> Agregar Historial
                </a>
            </div>
        </div>
    </div>
</section>
