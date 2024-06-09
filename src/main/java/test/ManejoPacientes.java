package test;

import datos.Conexion;
import datos.PacienteDaoJDBC;
import datos.PacienteDaoJDBC;
import domain.Paciente;
import domain.Paciente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ManejoPacientes {

    public static void main(String[] args) {

        // Creación de un objeto de la clase Conexion y llamada de los métodos de la clase DAO
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            PacienteDaoJDBC pacienteDAO = new PacienteDaoJDBC(conexion);

            // Insertar un nuevo objeto de la clase en la base de datos
            /*Paciente pacienteNuevo = new Paciente();
            pacienteNuevo.setCargo("Enfermera");
            pacienteDAO.insertar(pacienteNuevo);*/


            // Actualizar un registro existente en la base de datos
            /*Paciente pacienteModificado = new Paciente();
            pacienteModificado.setIdPaciente(3);
            pacienteModificado.setCargo("Radiólogo");
            pacienteDAO.actualizar(pacienteModificado);*/

            // Eliminar un registro de la base de datos
            /*pacienteDAO.eliminar(new Paciente(1));*/

            // Commit de la transacción
            conexion.commit();
            System.out.println("Se ha hecho el commit de la transacción");

            // Listar los registros existentes en la base de datos
            /*List<Paciente> pacientes = pacienteDAO.seleccionar();
            for (int i = 0; i < pacientes.size(); i++) {
                System.out.println("Registro " + (i + 1) + ": " + pacientes.get(i));
            }*/

            // Obtener y mostrar un solo registro
            Paciente paciente = pacienteDAO.seleccionarPorId(8);
            System.out.println("Paciente: " + paciente);

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
