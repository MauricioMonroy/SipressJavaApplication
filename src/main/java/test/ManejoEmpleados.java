package test;

import datos.*;
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
            EmpleadoDAO empleado = new EmpleadoDaoJDBC(conexion);

            // Actualizar un registro en la base de datos
            /*Empleado empleadoModificado = new Empleado();
            empleadoModificado.setIdEmpleado(1);
            empleadoModificado.setCargo("Médico General");            
            empleado.actualizar(empleadoModificado);*/

            // Insertar un registro en la base de datos
            Empleado nuevoEmpleado = new Empleado();
            nuevoEmpleado.setCargo("Internista");
            empleado.insertar(nuevoEmpleado);

            // Eliminar un registro de la base de datos
            /*empleado.eliminar(new Empleado(3));*/

            // Commit de la transacción
            conexion.commit();
            System.out.println("Se ha hecho el commit de la transacción");

            // Listar los registros existentes en la base de datos
            List<Empleado> empleados = empleado.seleccionar();
            empleados.forEach(empleadosLista -> {
                System.out.println("Registros: " + empleadosLista);
            });

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Se llama al método rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
}
