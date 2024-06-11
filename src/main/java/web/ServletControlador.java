package web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Usuario que visita por primera vez el sitio
        boolean nuevoUsuario = true;
        Cookie[] cookies = request.getCookies();

        // Usuario registrado
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("visitanteRegistrado") && cookie.getValue().equals("si")) {
                    nuevoUsuario = false;
                }
            }
        }

        String mensaje = null;
        if (nuevoUsuario) {
            Cookie nuevoUsuarioCookie = new Cookie("visitanteRegistrado", "si");
            response.addCookie(nuevoUsuarioCookie);
            mensaje = "Bienvenido al sitio";
        } else {
            mensaje = "Bienvenido de nuevo al sitio";
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(mensaje);


        // Se leen los parámetros del formulario
        /*String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        System.out.println("usuario = " + usuario);
        System.out.println("password = " + password);

        PrintWriter out = response.getWriter();*/

    }
}