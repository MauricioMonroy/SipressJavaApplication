package test;

import java.sql.*;

public class TestPreliminarMySqlJDBC {
    public static void main(String[] args) {
        var url = "jdbc:mysql://localhost:3306/database_sipress?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver"); //Puede ser necesario para versión Web
            Connection conexion = DriverManager.getConnection(url, "root", "Tiaerpoal533");

            // Creación del objeto que permite la ejecución de sentencias en la base de datos
            Statement sentencia = conexion.createStatement();

            // Ejecución de la sentencia SQL
            var sql = "SELECT id_usuario, username, password, nombre, apellido, identificacion, telefono, email, es_paciente, es_empleado FROM usuario";
            ResultSet resultado = sentencia.executeQuery(sql);

            // Iteración de los elementos para obtener todos los registros
            while (resultado.next()) {
                System.out.print("Id Usuario: " + resultado.getInt("id_usuario"));
                System.out.println("|Username: " + resultado.getString("username"));
                System.out.println("|Password: " + resultado.getString("password"));
                System.out.print(" |Nombre: " + resultado.getString("nombre"));
                System.out.print(" |Apellido: " + resultado.getString("apellido"));
                System.out.print(" |Identificación: " + resultado.getString("identificacion"));
                System.out.print(" |Teléfono: " + resultado.getString("telefono"));
                System.out.print(" |Email: " + resultado.getString("email"));
                System.out.println("|Es paciente: " + resultado.getBoolean("es_paciente"));
                System.out.println("|Es empleado: " + resultado.getBoolean("es_empleado"));
                System.out.println();
            }

            // Métodos para cerrar la conexión una vez recuperado los objetos
            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
