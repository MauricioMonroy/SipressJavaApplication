<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section id="actions">
    <div class="container">
        <div class="row">
            <!-- Botón para mostrar el número de pacientes -->
            <div class="col-12 col-md-4 d-flex justify-content-center">
                <a href="#" class="btn btn-info"
                   data-bs-toggle="modal" data-bs-target="#cantidadPacientesModal">
                    <i class="fa-solid fa-clipboard-question"></i> Total Registros
                </a>
            </div>
            <!-- Botón para agregar un nuevo registro -->
            <div class="col-12 col-md-4 d-flex justify-content-center">
                <a href="#" class="btn btn-success"
                   data-bs-toggle="modal" data-bs-target="#registrarPacienteModal">
                    <i class="fa-solid fa-user-plus"></i> Agregar Registro
                </a>
            </div>
        </div>
    </div>
</section>
