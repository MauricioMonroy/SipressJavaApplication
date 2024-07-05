package web;

import datos.HistorialDaoJDBC;
import domain.Historial;
import domain.Paciente;
import domain.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/ServletHistorial")
public class ServletHistorial extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "actualizar":
                        this.actualizarHistorial(request, response);
                        break;
                    case "seleccionar":
                        this.seleccionarRegistro(request, response);
                        break;
                    case "eliminar":
                        this.eliminarHistorial(request, response);
                        break;
                    default:
                        this.accionDefault(request, response);
                        break;
                }
            } else {
                this.accionDefault(request, response);
            }
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        List<Historial> historiales = new HistorialDaoJDBC().seleccionar();
        HttpSession session = request.getSession();
        session.setAttribute("historiales", historiales);
        session.setAttribute("totalHistoriales", historiales.size());
        response.sendRedirect("historiales.jsp");
    }

    private void seleccionarRegistro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int idHistorial = Integer.parseInt(request.getParameter("idHistorial"));
        Historial historial = new HistorialDaoJDBC().seleccionarPorId(idHistorial);
        if (historial == null) {
            request.setAttribute("errorMessage", "Historial no encontrado");
            request.getRequestDispatcher("/WEB-INF/paginas/historial/error.jsp").forward(request, response);
            return;
        }
        request.setAttribute("historial", historial);
        String jspMostrarHistorial = "/WEB-INF/paginas/historial/mostrarHistorial.jsp";
        request.getRequestDispatcher(jspMostrarHistorial).forward(request, response);
    }

    private void actualizarHistorial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int idHistorial = Integer.parseInt(request.getParameter("idHistorial"));
        Historial historial = new HistorialDaoJDBC().seleccionarPorId(idHistorial);
        if (historial == null) {
            request.setAttribute("errorMessage", "Historial no encontrado");
            request.getRequestDispatcher("/WEB-INF/paginas/historial/error.jsp").forward(request, response);
            return;
        }
        request.setAttribute("historial", historial);
        String jspActualizar = "/WEB-INF/paginas/historial/actualizarHistorial.jsp";
        request.getRequestDispatcher(jspActualizar).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "insertar":
                        this.insertarHistorial(request, response);
                        break;
                    case "modificar":
                        this.modificarHistorial(request, response);
                        break;
                    default:
                        this.accionDefault(request, response);
                        break;
                }
            } else {
                this.accionDefault(request, response);
            }
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    private void insertarHistorial(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {

        // Recoger parámetros del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String identificacion = request.getParameter("identificacion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String detalleEps = request.getParameter("detalleEps");
        Date fechaConsulta = Date.valueOf(request.getParameter("fechaConsulta"));
        String motivoConsulta = request.getParameter("motivoConsulta");
        Date fechaNacimiento = Date.valueOf(request.getParameter("fechaNacimiento"));
        String sexo = request.getParameter("sexo");
        String direccion = request.getParameter("direccion");
        String ocupacion = request.getParameter("ocupacion");
        String contactoEmergencia = request.getParameter("contactoEmergencia");
        String nombreContactoEmergencia = request.getParameter("nombreContactoEmergencia");
        String alergias = request.getParameter("alergias");
        String condicionesPreexistentes = request.getParameter("condicionesPreexistentes");
        String medicamentosActuales = request.getParameter("medicamentosActuales");
        String historialVacunas = request.getParameter("historialVacunas");
        String grupoSanguineo = request.getParameter("grupoSanguineo");
        String notasAdicionales = request.getParameter("notasAdicionales");

        // Crear objetos de dominio
        Usuario usuario = new Usuario(nombre, apellido, identificacion, telefono, email, true, false);
        Paciente paciente = new Paciente(detalleEps, fechaConsulta, usuario);

        // Crear objeto Historial
        Historial historial = new Historial(motivoConsulta, fechaNacimiento, sexo, direccion, ocupacion, contactoEmergencia,
                nombreContactoEmergencia, alergias, condicionesPreexistentes, medicamentosActuales, historialVacunas,
                grupoSanguineo, notasAdicionales, new Timestamp(System.currentTimeMillis()), paciente);

        int registrosModificados = new HistorialDaoJDBC().insertar(historial);
        System.out.println("registrosModificados = " + registrosModificados);

        if (registrosModificados > 0) {
            request.setAttribute("successMessage", "El registro se ha realizado exitosamente.");
        }

        this.accionDefault(request, response);
    }

    private void modificarHistorial(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        // Recoger parámetros del formulario
        int idHistorial = Integer.parseInt(request.getParameter("idHistorial"));
        int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String identificacion = request.getParameter("identificacion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String detalleEps = request.getParameter("detalleEps");
        Date fechaConsulta = Date.valueOf(request.getParameter("fechaConsulta"));
        String motivoConsulta = request.getParameter("motivoConsulta");
        Date fechaNacimiento = Date.valueOf(request.getParameter("fechaNacimiento"));
        String sexo = request.getParameter("sexo");
        String direccion = request.getParameter("direccion");
        String ocupacion = request.getParameter("ocupacion");
        String contactoEmergencia = request.getParameter("contactoEmergencia");
        String nombreContactoEmergencia = request.getParameter("nombreContactoEmergencia");
        String alergias = request.getParameter("alergias");
        String condicionesPreexistentes = request.getParameter("condicionesPreexistentes");
        String medicamentosActuales = request.getParameter("medicamentosActuales");
        String historialVacunas = request.getParameter("historialVacunas");
        String grupoSanguineo = request.getParameter("grupoSanguineo");
        String notasAdicionales = request.getParameter("notasAdicionales");
        Timestamp ultimaActualizacion = Timestamp.valueOf(request.getParameter("ultimaActualizacion"));

        // Crear objetos de dominio
        Usuario usuario = new Usuario(nombre, apellido, identificacion, telefono, email, true, false);
        usuario.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
        Paciente paciente = new Paciente(idPaciente, detalleEps, fechaConsulta, usuario);
        Historial historial = new Historial(idHistorial, motivoConsulta, fechaNacimiento, sexo, direccion, ocupacion,
                contactoEmergencia, nombreContactoEmergencia, alergias, condicionesPreexistentes, medicamentosActuales,
                historialVacunas, grupoSanguineo, notasAdicionales, ultimaActualizacion, paciente);

        int registrosModificados = new HistorialDaoJDBC().actualizar(historial);
        System.out.println("registrosModificados = " + registrosModificados);
        this.accionDefault(request, response);
    }

    private void eliminarHistorial(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        try {
            int idHistorial = Integer.parseInt(request.getParameter("idHistorial"));
            Historial historial = new Historial(idHistorial);
            int registrosModificados = new HistorialDaoJDBC().eliminar(historial);
            System.out.println("registrosModificados = " + registrosModificados);
            this.accionDefault(request, response);
        } catch (NumberFormatException e) {
            System.err.println("El parámetro idHistorial no es un número válido.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al eliminar el historial.");
            e.printStackTrace();
        }
    }
}

