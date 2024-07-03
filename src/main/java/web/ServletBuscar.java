package web;

import datos.EmpleadoDaoJDBC;
import datos.PacienteDaoJDBC;
import datos.UsuarioDaoJDBC;
import domain.Empleado;
import domain.Paciente;
import domain.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ServletBuscar")
public class ServletBuscar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getParameter("query");
        List<Usuario> resultadosUsuarios = new ArrayList<>();
        List<Paciente> resultadosPacientes = new ArrayList<>();
        List<Empleado> resultadosEmpleados = new ArrayList<>();

        try {
            if (query != null && !query.isEmpty()) {
                UsuarioDaoJDBC usuarioDao = new UsuarioDaoJDBC();
                PacienteDaoJDBC pacienteDao = new PacienteDaoJDBC();
                EmpleadoDaoJDBC empleadoDao = new EmpleadoDaoJDBC();

                resultadosUsuarios = usuarioDao.buscar(query);
                resultadosPacientes = pacienteDao.buscar(query);
                resultadosEmpleados = empleadoDao.buscar(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("resultadosUsuarios", resultadosUsuarios);
        request.setAttribute("resultadosPacientes", resultadosPacientes);
        request.setAttribute("resultadosEmpleados", resultadosEmpleados);
        request.getRequestDispatcher("/WEB-INF/paginas/comunes/resultadosBusqueda.jsp").forward(request, response);
    }
}

