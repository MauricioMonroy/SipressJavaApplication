<%@ page contentType="text/html;charset=UTF-8" %>
<nav class="navbar navbar-expand-lg fixed-top navbar-dark" id="menu">
    <div class="container-md">
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
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="index.jsp"
                    >Inicio</a
                    >
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ServletUsuario"
                    >Usuarios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ServletPaciente"
                    >Pacientes</a
                    >
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ServletEmpleado"
                    >Personal</a
                    >
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#"
                       role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Servicios
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a class="dropdown-item" href="ServletHistorial">
                            Gestión de Historias Clínicas
                        </a>
                        </li>
                        <li>
                            <a class="dropdown-item disabled" aria-disabled="true"
                               href="#">
                                Gestión de Citas médicas
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
            <form class="d-flex" role="search" action="ServletBuscar" method="GET">
                <input class="form-control me-2" type="search" name="query" placeholder="Buscar registro..." aria-label="Search">
                <button class="btn btn-outline-success" type="submit">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
            </form>
        </div>
    </div>
</nav>
