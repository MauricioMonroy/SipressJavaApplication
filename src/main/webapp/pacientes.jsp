<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="es">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Pacientes - SIPRESS</title>
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
                    <a class="nav-link" aria-current="page" href="index.jsp"
                    >Inicio</a
                    >
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="servicios.jsp"
                    >Servicios</a
                    >
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="pacientes.jsp"
                    >Pacientes</a
                    >
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="usuarios.jsp"
                    >Usuarios</a
                    >
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="empleados.jsp"
                    >Personal Médico</a
                    >
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="contacto.jsp">Contacto</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link text-light"
                    ><i class="fas fa-user"></i>
                        <span>Usuario:</span>
                        <span>Current user</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="login.jsp" class="nav-link text-light"
                    ><i class="fas fa-user-times"></i>
                        <span>Salir</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Contenido de la sección Pacientes -->
<section>
    <div class="container my-5">
        <div class="card">
            <div id="gestion-salud">
                <div class="card-header">
                    <h2 class="text-center">Funciones Disponibles</h2>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-6 mb-4">
                            <a href="../templates/new_patient.html" class="text-decoration-none">
                                <div class="card text-center h-100 text-bg-success">
                                    <div class="card-header">
                                        <h5 class="card-title">Registro de Pacientes</h5>
                                    </div>
                                    <div class="card-body">
                                        <img
                                                src="images/perfil-card.png"
                                                class="card-img-top"
                                                alt="perfil-card.png"/>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-6 mb-4">
                            <a
                                    href="#"
                                    class="text-decoration-none"
                                    data-bs-toggle="modal"
                                    data-bs-target="#listarPacientesModal">
                                <div class="card text-center h-100 text-bg-info">
                                    <div class="card-header">
                                        <h5 class="card-title">Lista de Pacientes</h5>
                                    </div>
                                    <div class="card-body">
                                        <img
                                                src="images/patients-list.png"
                                                class="card-img-top"
                                                alt="patients-list.png"/>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-6 mb-4">
                            <a
                                    href="#"
                                    class="text-decoration-none"
                                    data-bs-toggle="modal"
                                    data-bs-target="#modificarPerfilModal">
                                <div class="card text-center h-100 text-bg-danger">
                                    <div class="card-header">
                                        <h5 class="card-title">Modificar Datos de Paciente</h5>
                                    </div>
                                    <div class="card-body">
                                        <img
                                                src="images/update-perfil.png"
                                                class="card-img-top"
                                                alt="update-perfil.png"/>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-6 mb-4">
                            <a
                                    href="../templates/appointments.html"
                                    class="text-decoration-none">
                                <div class="card text-center h-100 text-bg-warning">
                                    <div class="card-header">
                                        <h5 class="card-title">Gestionar Citas</h5>
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
            </div>
        </div>
    </div>
</section>

<!-- Modal lista de usuarios -->
<div
        class="modal fade"
        id="listarUsuariosModal"
        tabindex="-1"
        aria-labelledby="listarUsuariosModalLabel"
        aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content bg-secondary">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="listarUsuariosModalLabel">
                    Usuarios Registrados
                </h1>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close">
                </button>
            </div>
            <div class="modal-body">
                <div class="container">
                    <ul>
                        <c:forEach var="usuario" items="${usuarios}">
                            <li>
                                    ${usuario.idUsuario}
                                    ${usuario.username}
                                    ${usuario.nombre}
                                    ${usuario.apellido}
                                    ${usuario.identificacion}
                                    ${usuario.telefono}
                                    ${usuario.email}
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal para modificar perfil -->
<div
        class="modal fade"
        id="modificarPerfilModal"
        tabindex="-1"
        aria-labelledby="modificarPerfilModalLabel"
        aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content bg-secondary">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="modificarPerfilModalLabel">
                    Modificar Datos
                </h1>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-floating mb-3">
                        <input
                                type="text"
                                class="form-control"
                                id="floatingInput"
                                placeholder="Nombre"/>
                        <label for="floatingInput">Nombre(s)</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input
                                type="text"
                                class="form-control"
                                id="floatingInput"
                                placeholder="Apellidos"/>
                        <label for="floatingInput">Apellidos</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input
                                type="email"
                                class="form-control"
                                id="floatingInput"
                                placeholder="nombre@ejemplo.com"/>
                        <label for="floatingInput">Correo electrónico</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input
                                type="password"
                                class="form-control"
                                id="floatingPassword"
                                placeholder="Contrasena Anterior"/>
                        <label for="floatingPassword">Contraseña Anterior</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input
                                type="password"
                                class="form-control"
                                id="floatingPassword"
                                placeholder="Nueva Contrasena"/>
                        <label for="floatingPassword">Nueva Contraseña</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input
                                type="password"
                                class="form-control"
                                id="floatingPassword"
                                placeholder="Confirmar password"/>
                        <label for="floatingConfirmPassword"
                        >Confirmar contraseña</label
                        >
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal">
                    Cerrar
                </button>
                <button type="submit" class="btn btn-primary">
                    Guardar
                </button>
            </div>
        </div>
    </div>
</div>
<!-- Modal para el formulario de registro nuevos usuarios -->
<div
        class="modal fade"
        id="registroUsuariosModal"
        tabindex="-1"
        aria-labelledby="registroUsuariosModalLabel"
        aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content bg-secondary">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="registroUsuariosModalLabel">
                    Registro de Usuarios
                </h1>
                <button
                        type="button"
                        class="btn btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close">
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingInput" placeholder="Nombre"/>
                        <label for="floatingInput">Nombre(s)</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingInput" placeholder="Apellidos"/>
                        <label for="floatingInput">Apellidos</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" id="floatingInput"
                               placeholder="nombre@ejemplo.com"/>
                        <label for="floatingInput">Correo electrónico</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="floatingPassword"
                               placeholder="password"/>
                        <label for="floatingPassword">Contraseña</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="floatingConfirmPassword"
                               placeholder="Confirmar password"/>
                        <label for="floatingConfirmPassword">Confirmar contraseña</label>
                    </div>
                </form>
            </div>
            <div class="card modal-footer">
                <div class="form-check mb-3">
                    <div>
                        <label class="form-check-label" for="policeCheck">
                            Acepto los términos y la política de protección de datos
                        </label>
                        <input class="form-check-input" type="checkbox" value="" id="policeCheck"/>
                    </div>
                    <button class="btn btn-lg btn-primary btn-register mb-2" type="submit">Registrarse
                    </button>
                    <div class="text-center">
                            <span>¿Ya está registrado?
                                <a href="login.jsp">Ingrese aquí</a>
                            </span>
                    </div>
                </div>
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
