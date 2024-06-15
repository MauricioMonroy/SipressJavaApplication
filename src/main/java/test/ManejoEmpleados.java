package test;

import datos.Conexion;
import datos.EmpleadoDaoJDBC;
import domain.Empleado;
import domain.Empleado;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ManejoEmpleados {

    public static void main(String[] args) {

        // Creación de un objeto de la clase Conexion y llamada de los métodos de la clase DAO
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            EmpleadoDaoJDBC empleadoDAO = new EmpleadoDaoJDBC(conexion);

            // Insertar un nuevo objeto de la clase en la base de datos
            /*Empleado empleadoNuevo = new Empleado();
            empleadoNuevo.setCargo("Enfermera");
            empleadoDAO.insertar(empleadoNuevo);*/


            // Actualizar un registro existente en la base de datos
            /*Empleado empleadoModificado = new Empleado();
            empleadoModificado.setIdEmpleado(3);
            empleadoModificado.setCargo("Radiólogo");
            empleadoDAO.actualizar(empleadoModificado);*/

            // Eliminar un registro de la base de datos
            /*empleadoDAO.eliminar(new Empleado(1));*/

            // Commit de la transacción
            conexion.commit();
            System.out.println("Se ha hecho el commit de la transacción");

            // Listar los registros existentes en la base de datos
            List<Empleado> empleados = empleadoDAO.seleccionar();
            for (int i = 0; i < empleados.size(); i++) {
                System.out.println("Registro " + (i + 1) + ": " + empleados.get(i));
            }

            // Obtener y mostrar un solo registro
//            Empleado empleado = empleadoDAO.seleccionarPorId(2);
//            System.out.println("Empleado: " + empleado);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Se llama al método rollback");
            try {
                assert conexion != null;
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
}