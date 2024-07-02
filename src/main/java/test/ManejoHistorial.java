package test;

import datos.Conexion;
import datos.HistorialDaoJDBC;
import domain.Historial;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ManejoHistorial {
    public static void main(String[] args) {

        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            HistorialDaoJDBC historialDAO = new HistorialDaoJDBC(conexion);

            // Inserciones, actualizaciones o eliminaciones
            // ...


            // Commit de la transacción
            conexion.commit();
            System.out.println("Se ha hecho el commit de la transacción");

            // Listar los registros existentes en la base de datos
//            List<Historial> historiales = historialDAO.seleccionar();
//            for (int i = 0; i < historiales.size(); i++) {
//                System.out.println("Registro " + (i + 1) + ": " + historiales.get(i));
//            }

            // Obtener y mostrar un solo registro
            Historial historial = historialDAO.seleccionarPorId(2);
            System.out.println("Historial: " + historial);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Se llama al método rollback");
            try {
                if (conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        } finally {
            Conexion.close(conexion);
        }
    }
}

