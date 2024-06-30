<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Tarjeta informativa -->
<div
        class="modal fade"
        id="cantidadHistorialesModal"
        tabindex="-1"
        aria-labelledby="cantidadHistorialesModalLabel"
        aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content bg-light">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="cantidadHistorialesModalLabel">
                    Historias Clínicas
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
                            <i class="fa-solid fa-book-medical"></i>
                        </span>
                        <h3 class="modal-title display-6">
                            Número de Historias Clínicas almacenadas en la Base de Datos:
                        </h3>
                        <p class="display-1">${totalHistoriales}</p>
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