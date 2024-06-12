<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<html>
<head>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>Página de Inicio - SIPRESS</title>
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
<!-- Navbar con función responsive -->
<nav class="navbar navbar-expand-lg navbar-dark" id="menu">
    <div class="container px-lg-5">
        <a class="navbar-brand" href="index.jsp">
            <img
                    src="images/nb-logo.png"
                    width="384"
                    height="116"
                    class="logo-imagen"
                    alt="sipress-logo"/>
        </a>
        <button
                class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="index.jsp"
                    >Inicio</a
                    >
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../templates/register.html"
                    >Registrarse</a
                    >
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../templates/services.html"
                    >Servicios</a
                    >
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../templates/users.html">Usuarios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../templates/doctors.html"
                    >Staff Médico</a
                    >
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../templates/contact.html">Contacto</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link text-light"
                    ><i class="fas fa-user"></i>
                        <span>Usuario:</span>
                        <span>Current user</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="../login-sipress.html" class="nav-link text-light"
                    ><i class="fas fa-user-times"></i>
                        <span>Salir</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- Cabecero -->
<header class="py-3">
    <div class="container px-lg-3">
        <div class="p-2 p-lg-3 bg-light rounded-3 text-center">
            <div class="m-2 m-lg-3">
                <h1 class="fs-3 fw-bold"><c:out value="" ></h1>
                <p class="fs-6">
                    <span class="text-success fw-bold fs-5">SIPRESS </span> es una
                    aplicación que simpifica la gestión de las Instituciones
                    Prestadoras de Salud (IPS). Facilita el registro de pacientes, la
                    programación de citas y el control de recursos médicos. Su
                    objetivo es: mejorar la atención y la eficiencia en la prestación
                    de servicios hospitalarios.
                </p>
            </div>
        </div>
    </div>
</header>
<!-- Contenido de la página -->
<section class="pt-2">
    <div class="container px-lg-3">
        <!-- Características de la página -->
        <div class="row gx-lg-5" id="gestion-salud">
            <div class="row mt-3">
                <div class="col-md-3 col-sm-6">
                    <a href="../templates/users.html" class="text-decoration-none">
                        <div class="card text-center h-100">
                            <img
                                    src="images/Users.png"
                                    alt="users.png"
                                    class="card-img-top"/>

                            <div class="card-body">
                                <h5 class="card-title">Usuarios</h5>
                                <p>
                                    Aquí se puede crear, editar y eliminar su cuenta, además
                                    de consultar trámites relacionados.
                                </p>
                            </div>
                        </div>
                    </a>
                </div>

                <div class="col-md-3 col-sm-6">
                    <a href="../templates/doctors.html" class="text-decoration-none">
                        <div class="card text-center h-100">
                            <img
                                    src="images/equipo-medico.png"
                                    alt="equipo-medico.png"
                                    class="card-img-top"/>
                            <div class="card-body">
                                <h5 class="card-title">Staff Médico</h5>
                                <p>
                                    Aquí se pueden conocer los detalles del staff médico, sus
                                    funciones y horarios disponibles.
                                </p>
                            </div>
                        </div>
                    </a>
                </div>

                <div class="col-md-3 col-sm-6">
                    <a href="../templates/services.html" class="text-decoration-none">
                        <div class="card text-center h-100">
                            <img
                                    src="images/servicios.png"
                                    alt="servicios.png"
                                    class="card-img-top"/>
                            <div class="card-body">
                                <h5 class="card-title">Servicios Disponibles</h5>
                                <p>
                                    Aquí se puede conocer el portafolio de servicios y
                                    gestionar trámites médicos de la institución.
                                </p>
                            </div>
                        </div>
                    </a>
                </div>

                <div class="col-md-3 col-sm-6">
                    <a href="../templates/contact.html" class="text-decoration-none">
                        <div class="card text-center h-100">
                            <img
                                    src="images/contacto.png"
                                    alt="contacto.png"
                                    class="card-img-top"/>
                            <div class="card-body">
                                <h5 class="card-title">Contacto</h5>
                                <p>
                                    Aquí se pueden gestionar sus PQRS y consultar cualquier
                                    inconveniente con los servicios prestados.
                                </p>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <br/>
</section>
<!-- Pie de página -->
<footer class="py-5" id="iconos">
    <div class="py-2 text-center">
        <span class="text-light"> Codelicht Software Solutions&copy;</span>
        <p class="text-light text-center mb-0 p-2 fs-6">
            Todos los derechos reservados 2024
        </p>
        <div class="text-center">
            <a
                    href="https://zajuna.sena.edu.co/"
                    aria-label="Plataforma Zajuna SENA"
                    rel="noopener"
                    target="_blank"
            ><i class="fa-solid fa-globe"></i
            ></a>
            <a
                    href="https://call.whatsapp.com/voice/JqIwkDwqS2qpUUqbFjMwwd"
                    aria-label="Contacto Whatsapp"
                    rel="noopener"
                    target="_blank"
            ><i class="fa-brands fa-whatsapp"></i
            ></a>
            <a
                    href="https://t.me/+GVvMDQ4p8p84ZWJh"
                    aria-label="Contacto Telegram"
                    rel="noopener"
                    target="_blank"
            ><i class="fa-brands fa-telegram"></i
            ></a>
        </div>
    </div>
</footer>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Enlace a Bootstrap JS -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
