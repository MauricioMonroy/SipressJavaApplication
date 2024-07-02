<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<!-- Head común a todas lás vistas -->
<jsp:include page="/WEB-INF/paginas/comunes/head.jsp"/>
<body>
<!-- Navbar con función responsive-->
<jsp:include page="/WEB-INF/paginas/comunes/navbar.jsp"/>
<!--Cabecero-->
<jsp:include page="/WEB-INF/paginas/historial/cabecero.jsp"/>

<!-- Contenido de la página -->
<!-- Botones de navegación -->
<jsp:include page="/WEB-INF/paginas/historial/botonesEdicionHistorial.jsp"/>
<!-- Detalle del Historial -->
<section id="detalleHistorial">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-9">
                <div class="card">
                    <div class="card-header">
                        <h1 class="fs-5">
                            Detalle del Historial Médico
                        </h1>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Historial ID: ${historial.idHistorial}</h5>
                        <p class="card-text"><strong>Paciente:</strong>
                            ${historial.paciente.usuario.nombre}
                            ${historial.paciente.usuario.apellido}</p>
                        <p class="card-text"><strong>Identificación:</strong>
                            ${historial.paciente.usuario.identificacion}</p>
                        <p class="card-text"><strong>Motivo de
                            Consulta:</strong> ${historial.motivoConsulta}</p>
                        <p class="card-text"><strong>Fecha de
                            Nacimiento:</strong> ${historial.fechaNacimiento}</p>
                        <p class="card-text"><strong>Sexo:</strong> ${historial.sexo}</p>
                        <p class="card-text"><strong>Dirección:</strong> ${historial.direccion}</p>
                        <p class="card-text"><strong>Ocupación:</strong> ${historial.ocupacion}</p>
                        <p class="card-text"><strong>Contacto de
                            Emergencia:</strong> ${historial.contactoEmergencia}</p>
                        <p class="card-text"><strong>Nombre del Contacto de
                            Emergencia:</strong> ${historial.nombreContactoEmergencia}</p>
                        <p class="card-text"><strong>Alergias:</strong> ${historial.alergias}</p>
                        <p class="card-text"><strong>Condiciones
                            Preexistentes:</strong> ${historial.condicionesPreexistentes}</p>
                        <p class="card-text"><strong>Medicamentos
                            Actuales:</strong> ${historial.medicamentosActuales}</p>
                        <p class="card-text"><strong>Historial de
                            Vacunas:</strong> ${historial.historialVacunas}
                        </p>
                        <p class="card-text"><strong>Grupo Sanguíneo:</strong> ${historial.grupoSanguineo}
                        </p>
                        <p class="card-text"><strong>Notas
                            Adicionales:</strong> ${historial.notasAdicionales}</p>
                        <p class="card-text"><strong>Última
                            Actualización:</strong> ${historial.ultimaActualizacion}
                        </p>
                    </div>
                    <div class="card-footer">
                        <!-- Botón para imprimir la vista -->
                        <div class="col-12 d-flex justify-content-center">
                            <button class="btn btn-secondary" onclick="printCard()">
                                <i class="fa-solid fa-print"></i> Imprimir
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Pie de página -->
<jsp:include page="/WEB-INF/paginas/comunes/footer.jsp"/>
<!-- Script para el botón de imprimir -->
<script>
    function printCard() {
        var divContents = document.querySelector('#detalleHistorial .card').innerHTML;
        var printWindow = window.open('', '', 'height=600,width=800');
        printWindow.document.write('<html><head><title>Imprimir Historial</title>');
        printWindow.document.write('<link rel="stylesheet" href="path/to/your/bootstrap.css">');
        printWindow.document.write('<style>/* Estilos específicos para la impresión */</style>');
        printWindow.document.write('</head><body >');
        printWindow.document.write('<div class="card">');
        printWindow.document.write(divContents);
        printWindow.document.write('</div>');
        printWindow.document.write('</body></html>');
        printWindow.document.close();
        printWindow.print();
    }
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Enlace a Bootstrap JS -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
