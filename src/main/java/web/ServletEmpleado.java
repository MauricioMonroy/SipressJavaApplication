package web;

import datos.EmpleadoDaoJDBC;
import domain.Empleado;
import domain.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ServletEmpleado")
public class ServletEmpleado extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "actualizar":
                        this.actualizarEmpleado(request, response);
                        break;
                    case "eliminar":
                        this.eliminarEmpleado(request, response);
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
        List<Empleado> empleados = new EmpleadoDaoJDBC().seleccionar();
        HttpSession session = request.getSession();
        session.setAttribute("empleados", empleados);
        session.setAttribute("totalEmpleados", empleados.size());
        response.sendRedirect("empleados.jsp");
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        Empleado empleado = new EmpleadoDaoJDBC().seleccionarPorId(idEmpleado);
        if (empleado == null) {
            request.setAttribute("errorMessage", "Empleado no encontrado");
            request.getRequestDispatcher("/WEB-INF/paginas/empleado/error.jsp").forward(request, response);
            return;
        }
        request.setAttribute("empleado", empleado);
        String jspActualizar = "/WEB-INF/paginas/empleado/actualizarEmpleado.jsp";
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
                        this.insertarEmpleado(request, response);
                        break;
                    case "modificar":
                        this.modificarEmpleado(request, response);
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

    private void insertarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        String cargo = request.getParameter("cargo");

        // Datos del usuario asociado
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String identificacion = request.getParameter("identificacion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");

        // Crear objetos de dominio
        Usuario usuario = new Usuario(nombre, apellido, identificacion, telefono, email, true, false);
        Empleado empleado = new Empleado(cargo, usuario);

        int registrosModificados = new EmpleadoDaoJDBC().insertar(empleado);
        System.out.println("registrosModificados = " + registrosModificados);

        this.accionDefault(request, response);
    }

    private void modificarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        String cargo = request.getParameter("cargo");

        // Datos del usuario asociado
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String identificacion = request.getParameter("identificacion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");

        Empleado empleado = new Empleado(idEmpleado, cargo, new Usuario(nombre, apellido, identificacion, telefono, email));
        int registrosModificados = new EmpleadoDaoJDBC().actualizar(empleado);
        System.out.println("registrosModificados = " + registrosModificados);
        this.accionDefault(request, response);
    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        try {
            int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
            Empleado empleado = new Empleado(idEmpleado);
            int registrosModificados = new EmpleadoDaoJDBC().eliminar(empleado);
            System.out.println("registrosModificados = " + registrosModificados);
            this.accionDefault(request, response);
        } catch (NumberFormatException e) {
            System.err.println("El parámetro idEmpleado no es un número válido.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al eliminar el empleado.");
            e.printStackTrace();
        }
    }
}
