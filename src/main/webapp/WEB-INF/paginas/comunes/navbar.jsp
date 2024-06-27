<%@ page contentType="text/html;charset=UTF-8" %>
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
                    <a class="nav-link" href="ServletControlador"
                    >Usuarios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="pacientes.jsp"
                    >Pacientes</a
                    >
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="servicios.jsp"
                    >Servicios</a
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
