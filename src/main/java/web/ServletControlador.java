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
//                    case "eliminar":
//                        this.eliminarUsuario(request, response);
//                        break;
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


    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Usuario> usuarios = new UsuarioDaoJDBC().seleccionar();
        System.out.println("usuarios = " + usuarios);
        HttpSession session = request.getSession();
        session.setAttribute("usuarios", usuarios);
        session.setAttribute("totalUsuarios", usuarios.size());
        response.sendRedirect("lista-usuarios.jsp");
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        Usuario usuario = new UsuarioDaoJDBC().seleccionarPorId(new Usuario(idUsuario).getIdUsuario());
        request.setAttribute("usuario", usuario);
        String jspActualizar = "/WEB-INF/paginas/usuario/actualizarUsuario.jsp";
        request.getRequestDispatcher(jspActualizar).forward(request, response);
    }

}
