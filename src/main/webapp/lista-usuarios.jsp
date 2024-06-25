<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Lista de Usuarios - SIPRESS</title>
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
<jsp:include page="/WEB-INF/paginas/comunes/navbar.jsp"/>
<!-- Botones de navegación -->
<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacion.jsp"/>

<section id="contenedor-lista">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header bg-primary text-light">
                        <h2 class="text-center">
                            <i class="fa-regular fa-address-book"></i> Lista de Usuarios
                        </h2>
                    </div>
                    <div class="card-body">
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
</section>

<!-- Modal formulario de registro nuevos usuarios -->
<div
        class="modal fade"
        id="registrarUsuarioModal"
        tabindex="-1"
        aria-labelledby="registrarUsuarioModalLabel"
        aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content bg-light">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="registrarUsuarioModalLabel">
                    Registro de Usuarios
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
                               placeholder="Contrasena"/>
                        <label for="floatingPassword">Contraseña</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="floatingConfirmPassword"
                               placeholder="Confirmar contrasena"/>
                        <label for="floatingConfirmPassword">Confirmar contraseña</label>
                    </div>

                    <div class="form-check mb-3">
                        <div>
                            <label class="form-check-label" for="PoliceCheck">
                                Acepto los términos y la política de protección de datos
                                <input class="form-check-input" type="checkbox" value="" id="PoliceCheck"/>
                            </label>
                        </div>
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
                    Registrarse
                </button>
            </div>
            <div>
                    <span>¿Ya está registrado?
                    <a href="login.jsp">
                        Ingrese aquí
                    </a>
                </span>
            </div>
        </div>
    </div>
</div>
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