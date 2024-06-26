<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>Servicios - SIPRESS</title>
    <!-- Bootstrap core CSS -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"/>
    <!-- Fontawesome íconos -->
    <script
            src="https://kit.fontawesome.com/dc97c04e5e.js"
            crossorigin="anonymous"></script>
    <!-- Enlace a hoja de estilos CSS -->
    <link href="css/main.css" rel="stylesheet"/>
    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="images/favicon.ico"/>
</head>
<body>
<jsp:include page="WEB-INF/paginas/comunes/navbar.jsp"/>
<section>
    <div class="container my-5">
        <div class="card">
            <div id="gestion-salud">
                <div class="card-header">
                    <h2 class="text-center">
                        <i class="fa-solid fa-hospital-user"></i> Control de Pacientes
                    </h2>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-6 mb-4">
                            <a
                                    href="citas.jsp"
                                    class="text-decoration-none">
                                <div class="card text-center h-100 text-bg-info">
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
                        <div class="col-md-6 mb-4">
                            <a
                                    href="historiales.html"
                                    class="text-decoration-none">
                                <div class="card text-center h-100 text-bg-success">
                                    <div class="card-header">
                                        <h5 class="card-title">Gestión de Historiales</h5>
                                    </div>
                                    <div class="card-body">
                                        <img
                                                src="images/update-patient.png"
                                                class="card-img-top"
                                                alt="citas-card.png"/>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="WEB-INF/paginas/comunes/footer.jsp"/>
</body>
</html>
