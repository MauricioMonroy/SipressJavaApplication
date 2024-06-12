package web;

import domain.Perfil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    request.setAttribute("mensaje", "¡Bienvenido a SIPRESS!");
    RequestDispatcher rd = request.getRequestDispatcher("webapp/index.jsp");
    rd.forward(request, response);
    }
}