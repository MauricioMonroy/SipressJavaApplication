<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
<section id="contenedor-registro">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header bg-primary text-light">
                        <h2 class="text-center">
                            <i class="fa-solid fa-users"></i>Control Usuarios
                        </h2>
                    </div>
                    <div class="card-body">
                        <!-- Formulario de registro nuevos usuarios -->
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
                                <input class="form-check-input" type="checkbox" value="" id="PolicieCheck"/>
                                <div>
                                    <label class="form-check-label" for="PolicieCheck">
                                        Acepto los términos y la política de protección de datos
                                    </label>
                                </div>
                            </div>
                            <div class="d-grid">
                                <button class="btn btn-lg btn-primary btn-register mb-2" type="submit">Registrarse
                                </button>
                                <div class="text-center">
                                    <span>¿Ya está registrado? <a href="../login-sipress.html" th:href="@{/login}">Ingrese aquí</a></span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
