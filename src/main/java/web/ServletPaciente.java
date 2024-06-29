package web;

import datos.PacienteDaoJDBC;
import domain.Paciente;
import domain.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/ServletPaciente")
public class ServletPaciente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "actualizar":
                        this.actualizarPaciente(request, response);
                        break;
                    case "eliminar":
                        this.eliminarPaciente(request, response);
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
        List<Paciente> pacientes = new PacienteDaoJDBC().seleccionar();
        HttpSession session = request.getSession();
        session.setAttribute("pacientes", pacientes);
        session.setAttribute("totalPacientes", pacientes.size());
        response.sendRedirect("pacientes.jsp");
    }

    private void actualizarPaciente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
        Paciente paciente = new PacienteDaoJDBC().seleccionarPorId(idPaciente);
        if (paciente == null) {
            request.setAttribute("errorMessage", "Paciente no encontrado");
            request.getRequestDispatcher("/WEB-INF/paginas/paciente/error.jsp").forward(request, response);
            return;
        }
        request.setAttribute("paciente", paciente);
        String jspActualizar = "/WEB-INF/paginas/paciente/actualizarPaciente.jsp";
        request.getRequestDispatcher(jspActualizar).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "insertar":
                        this.insertarPaciente(request, response);
                        break;
                    case "modificar":
                        this.modificarPaciente(request, response);
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

    private void insertarPaciente(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException, ServletException {
        String detalleEps = request.getParameter("detalleEps");
        // Convierte la cadena de fecha a java.util.Date
        String fechaConsultaStr = request.getParameter("fechaConsulta");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaConsulta = null;
        try {
            fechaConsulta = dateFormat.parse(fechaConsultaStr);
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Formato de fecha incorrecto.");
            request.getRequestDispatcher("/WEB-INF/paginas/paciente/registrarPaciente.jsp").forward(request, response);
            return;
        }

        // Datos del usuario asociado
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String identificacion = request.getParameter("identificacion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");

        // Validación de contraseñas
        if (!password.equals(repeatPassword)) {
            request.setAttribute("errorMessage", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("/WEB-INF/paginas/paciente/registrarPaciente.jsp").forward(request, response);
            return;
        }

        // Crear objetos de dominio
        Usuario usuario = new Usuario(username, password, nombre, apellido, identificacion, telefono, email, true, false);
        Paciente paciente = new Paciente(detalleEps, fechaConsulta, usuario);

        int registrosModificados = new PacienteDaoJDBC().insertar(paciente);
        System.out.println("registrosModificados = " + registrosModificados);

        this.accionDefault(request, response);
    }

    private void modificarPaciente(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException, ServletException {
        int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
        String detalleEps = request.getParameter("detalleEps");

        // Convierte la cadena de fecha a java.util.Date
        String fechaConsultaStr = request.getParameter("fechaConsulta");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaConsulta = null;
        try {
            fechaConsulta = dateFormat.parse(fechaConsultaStr);
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Formato de fecha incorrecto.");
            request.getRequestDispatcher("/WEB-INF/paginas/paciente/registrarPaciente.jsp").forward(request, response);
            return;
        }

        // Datos del usuario asociado
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String identificacion = request.getParameter("identificacion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");

        Usuario usuario = new Usuario(nombre, apellido, identificacion, telefono, email);
        Paciente paciente = new Paciente(idPaciente, detalleEps, fechaConsulta, usuario);
        int registrosModificados = new PacienteDaoJDBC().actualizar(paciente);
        System.out.println("registrosModificados = " + registrosModificados);
        this.accionDefault(request, response);
    }

    private void eliminarPaciente(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        try {
            int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
            Paciente paciente = new Paciente(idPaciente);
            int registrosModificados = new PacienteDaoJDBC().eliminar(paciente);
            System.out.println("registrosModificados = " + registrosModificados);
            this.accionDefault(request, response);
        } catch (NumberFormatException e) {
            System.err.println("El parámetro idPaciente no es un número válido.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al eliminar el paciente.");
            e.printStackTrace();
        }
    }
}


