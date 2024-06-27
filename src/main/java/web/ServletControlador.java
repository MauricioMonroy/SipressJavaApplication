package web;

import datos.UsuarioDaoJDBC;
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

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "actualizar":
                        this.actualizarUsuario(request, response);
                        break;
                    case "eliminar":
                        this.eliminarUsuario(request, response);
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
        List<Usuario> usuarios = new UsuarioDaoJDBC().seleccionar();
        HttpSession session = request.getSession();
        session.setAttribute("usuarios", usuarios);
        response.sendRedirect("usuarios.jsp");
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        Usuario usuario = new UsuarioDaoJDBC().seleccionarPorId(new Usuario(idUsuario).getIdUsuario());
        request.setAttribute("usuario", usuario);
        String jspActualizar = "/WEB-INF/paginas/usuarios/actualizarUsuario.jsp";
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
                        this.insertarUsuario(request, response);
                        break;
                    case "modificar":
                        this.modificarUsuario(request, response);
                        break;
                    default:
                        this.accionDefault(request, response);
                }
            } else {
                this.accionDefault(request, response);
            }
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    private void insertarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String identificacion = request.getParameter("identificacion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        Boolean esPaciente = request.getParameter("esPaciente") != null ? Boolean.valueOf(request.getParameter("esPaciente")) : Boolean.FALSE;
        Boolean esEmpleado = request.getParameter("esEmpleado") != null ? Boolean.valueOf(request.getParameter("esEmpleado")) : Boolean.FALSE;

        System.out.println("esPaciente = " + esPaciente);
        System.out.println("esEmpleado = " + esEmpleado);

        Usuario usuario = new Usuario(username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);

        int registrosModificados = new UsuarioDaoJDBC().insertar(usuario);
        System.out.println("registrosModificados = " + registrosModificados);

        this.accionDefault(request, response);
    }

    private void modificarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException, ServletException {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String identificacion = request.getParameter("identificacion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        Boolean esPaciente = request.getParameter("esPaciente") != null ? Boolean.valueOf(request.getParameter("esPaciente")) : Boolean.FALSE;
        Boolean esEmpleado = request.getParameter("esEmpleado") != null ? Boolean.valueOf(request.getParameter("esEmpleado")) : Boolean.FALSE;

        Usuario usuario = new Usuario(idUsuario, username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);

        int registrosModificados = new UsuarioDaoJDBC().actualizar(usuario);
        System.out.println("registrosModificados = " + registrosModificados);

        this.accionDefault(request, response);
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException, ServletException {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

        Usuario usuario = new Usuario(idUsuario);

        int registrosModificados = new UsuarioDaoJDBC().eliminar(usuario);
        System.out.println("registrosModificados = " + registrosModificados);

        this.accionDefault(request, response);
    }
}
