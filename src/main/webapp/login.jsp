<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Inicio de Sesión - SIPRESS</title>
    <!-- Enlace a Bootstrap CSS -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous" />
    <!-- Fontawesome íconos -->
    <script
            src="https://kit.fontawesome.com/dc97c04e5e.js"
            crossorigin="anonymous"></script>
    <!-- Enlace al archivo CSS -->
    <link href="css/login.css" rel="stylesheet" />
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="images/favicon.ico" />
</head>
<body>
    <div class="container-fluid ps-md-0 bg-dark text-light">
        <div class="row g-0">
            <div class="col-md-6 bg-image"></div>
            <div class="col-md-6">
                <div class="login d-flex align-items-center py-5">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-9 col-lg-8 mx-auto">
                                <h3 class="login-heading mb-4">Bienvenido a Sipress</h3>
                                <p>Por favor ingrese sus credenciales</p>
                                <!-- Formulario de inicio de sesión -->
                                <form
                                    name="form-login"
                                    id="loginForm"
                                    action="/login/ServletControlador"
                                    method="post">
                                    <div class="form-floating mb-3 text-dark">
                                        <input
                                                type="text"
                                                name="usuario"
                                                class="form-control"
                                                id="floatingInput"
                                                placeholder="nombre@ejemplo.com" />
                                        <label for="floatingInput">usuario</label>
                                    </div>
                                    <div class="form-floating mb-3 text-dark">
                                        <input
                                                type="password"
                                                name="password"
                                                class="form-control"
                                                id="floatingPassword"
                                                placeholder="Contrasena" />
                                        <label for="floatingPassword">Contraseña</label>
                                    </div>

                                    <div class="form-check mb-3">
                                        <input
                                                class="form-check-input"
                                                type="checkbox"
                                                value="recordar"
                                                id="rememberPasswordCheck" />
                                        <label
                                                class="form-check-label"
                                                for="rememberPasswordCheck">
                                            Recordar contraseña
                                        </label>
                                    </div>
                                    <div class="d-grid">
                                        <button
                                                class="btn btn-lg btn-primary btn-login text-uppercase fw-bold mb-2"
                                                type="submit" value="Ingresar">
                                            Ingresar
                                        </button>
                                        <div class="text-center">
                                            <a class="small" href="templates/contact.html"
                                            >¿Olvidó su contraseña?</a
                                            >
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Enlace al archivo JavaScript -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Enlace a Bootstrap JS -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
