<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="es">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Staff Médico - SIPRESS</title>
    <!-- Bootstrap core CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous" />
    <!-- Fontawesome íconos -->
    <script
      src="https://kit.fontawesome.com/dc97c04e5e.js"
      crossorigin="anonymous"></script>
    <!-- Enlace a hoja de estilos CSS -->
    <link href="../static/css/main.css" rel="stylesheet" />
    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="../static/images/favicon.ico" />
  </head>
  <body>
    <!-- Navbar con función responsive -->
    <jsp:include page="WEB-INF/paginas/comunes/navbar.jsp"/>
    <!-- Cabecero -->
    <header class="py-3">
      <div class="container px-lg-3">
        <div class="p-2 p-lg-3 bg-info rounded-3 text-center">
          <div class="m-2 m-lg-3">
            <h1 class="fs-3 fw-bold">Staff Médico de la Institución</h1>
            <p class="fs-6">
              Conozca a los miembros del personal médico, su especialidad y la
              manera en que puede contactarlos.
            </p>
          </div>
        </div>
      </div>
    </header>
    <!-- Contenido -->
    <section>
      <br />
      <div class="container">
        <div class="row">
          <div class="card">
            <div class="card-header text-center">
              <h3>Lista de médicos</h3>
            </div>
            <div>
              <table class="table table-striped table-responsive-md">
                <thead class="thead-dark">
                  <tr>
                    <th>Nombre y Apellido</th>
                    <th>Especialidad</th>
                    <th>Contacto</th>
                  </tr>
                </thead>
                <tbody>
                  <tr th:each="doctor : ${doctors}">
                    <td th:text="${doctor.fullNameDoctor}"></td>
                    <td th:text="${doctor.specialty}"></td>
                    <td th:text="${doctor.contact}"></td>
                  </tr>
                  <tr>
                    <td>Dr. Juan Pérez</td>
                    <td>Cardiología</td>
                    <td>juan.perez@example.com</td>
                  </tr>
                  <tr>
                    <td>Dra. María Rodríguez</td>
                    <td>Pediatría</td>
                    <td>maria.rodriguez@example.com</td>
                  </tr>
                  <tr>
                    <td>Dr. Carlos Sánchez</td>
                    <td>Neurología</td>
                    <td>carlos.sanchez@example.com</td>
                  </tr>
                  <tr>
                    <td>Dra. Ana Gómez</td>
                    <td>Ginecología</td>
                    <td>ana.gomez@example.com</td>
                  </tr>
                  <tr>
                    <td>Dr. Luis Martínez</td>
                    <td>Oftalmología</td>
                    <td>luis.martinez@example.com</td>
                  </tr>
                  <tr>
                    <td>Dr. Peter Pan</td>
                    <td>Internista</td>
                    <td>peter.pan@example.com</td>
                  </tr>
                </tbody>
              </table>
              <br />
              <div class="d-grid gap-2 col-4 mx-auto">
                <a class="btn btn-warning"
                  ><p class="h4">Buscar <i class="fa-solid fa-search"></i></p
                ></a>
              </div>
              <br />
            </div>
          </div>
        </div>
      </div>
    </section>
    <br />

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
