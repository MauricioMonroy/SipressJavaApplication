<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<!-- Head común a todas lás vistas -->
<jsp:include page="WEB-INF/paginas/comunes/head.jsp"/>
<body>
<!-- Navbar con función responsive -->
<jsp:include page="WEB-INF/paginas/comunes/navbar.jsp"/>

<!-- Cabecero -->
<header class="py-3">
    <div class="container">
        <div class="row bg-light rounded-3 text-center">
            <div class="m-2 m-lg-3">
                <h1 class="fs-3 fw-bold">
                    Bienvenido a <span class="text-success fw-bold fs-2">SIPRESS</span>
                </h1>
                <p class="fs-6">
                    El Sistema Integral para la Prestación de Servicios de Salud -SIPRESS
                    es una aplicación diseñada para simplificar la gestión de las
                    Instituciones Prestadoras de Salud (IPS). Facilita el registro de pacientes
                    y usuarios, así como la manipulación de historiales médicos y el control de
                    la información relacionada. Su objetivo principal es mejorar la atención
                    y la eficiencia en la prestación de servicios hospitalarios.
                </p>
            </div>
        </div>
    </div>
</header>

<!-- Contenido de la página -->
<section class="index">
    <div class="container">
        <!-- Características de la página -->
        <div class="row gx-lg-5">
            <div class="col-md-3 col-sm-6">
                <a href="ServletUsuario" class="text-decoration-none">
                    <div class="card text-center h-100 custom-card">
                        <div class="card-header">
                            <h3 class="card-title">Usuarios</h3>
                        </div>
                        <div class="card-body">
                            <img
                                    src="images/Users.png"
                                    alt="users.png"
                                    class="card-img-top"/>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-md-3 col-sm-6">
                <a href="ServletPaciente" class="text-decoration-none">
                    <div class="card text-center h-100 custom-card">
                        <div class="card-header">
                            <h3 class="card-title">Pacientes</h3>
                        </div>

                        <div class="card-body">
                            <img
                                    src="images/patients.png"
                                    alt="patients.png"
                                    class="card-img-top"/>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-md-3 col-sm-6">
                <a href="ServletEmpleado" class="text-decoration-none">
                    <div class="card text-center h-100 custom-card">
                        <div class="card-header">
                            <h3 class="card-title">Personal</h3>
                        </div>
                        <div class="card-body">
                            <img
                                    src="images/equipo-medico.png"
                                    alt="equipo-medico.png"
                                    class="card-img-top"/>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-md-3 col-sm-6">
                <a href="servicios.jsp" class="text-decoration-none">
                    <div class="card text-center h-100 custom-card">
                        <div class="card-header">
                            <h3 class="card-title">Servicios</h3>
                        </div>
                        <div class="card-body">
                            <img
                                    src="images/servicios.png"
                                    alt="servicios.png"
                                    class="card-img-top"/>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>
    <br/>
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
