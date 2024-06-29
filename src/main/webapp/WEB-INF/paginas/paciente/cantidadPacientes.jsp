<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Tarjeta informativa -->
<div
        class="modal fade"
        id="cantidadPacientesModal"
        tabindex="-1"
        aria-labelledby="cantidadPacientesModalLabel"
        aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content bg-light">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="cantidadPacientesModalLabel">
                    Pacientes Registrados
                </h1>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close">
                    <span></span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card text-center bg-success text-light mb-3">
                    <div class="card-body">
                        <span class="display-1">
                            <i class="fa-solid fa-users-viewfinder"></i>
                        </span>
                        <h3 class="modal-title display-6">
                            Número de pacientes
                            registrados:
                        </h3>
                        <p class="display-1">${totalPacientes}</p>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal">
                    Cerrar
                </button>
            </div>
        </div>
    </div>
</div>