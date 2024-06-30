<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<!-- Head común a todas lás vistas -->
<jsp:include page="WEB-INF/paginas/comunes/head.jsp"/>

<body>
<!-- Navbar con función responsive -->
<jsp:include page="WEB-INF/paginas/comunes/navbar.jsp"/>

<!-- Cabecero -->
<jsp:include page="WEB-INF/paginas/servicio/cabecero.jsp"/>

<!-- Contenido de la página -->
<section id="services">
    <div class="container" id="gestion-salud">
        <div class="row">
            <div class="col-md-6 mb-4">
                <a
                        href="ServletHistorial"
                        class="text-decoration-none">
                    <div class="card text-center h-100 text-bg-success">
                        <div class="card-header">
                            <h5 class="card-title">Gestión de Historiales</h5>
                        </div>
                        <div class="card-body">
                            <img
                                    src="images/update-patient.png"
                                    class="card-img-top"
                                    alt="update-patient.png"/>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-md-6 mb-4">
                <a
                        href="ServletCitas"
                        class="text-decoration-none">
                    <div class="card text-center h-100 text-bg-primary">
                        <div class="card-header">
                            <h5 class="card-title">Gestión de Citas</h5>
                        </div>
                        <div class="card-body">
                            <img
                                    src="images/citas-card.png"
                                    class="card-img-top"
                                    alt="citas-card.png"/>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>
</section>
<!-- Pie de página -->
<jsp:include page="WEB-INF/paginas/comunes/footer.jsp"/>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Enlace a Bootstrap JS -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
