package test;

import datos.UsuarioDaoJDBC;
import domain.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioDaoJDBCTest {

    private Connection connection;
    private UsuarioDaoJDBC usuarioDao;

    @BeforeEach
    public void setUp() throws SQLException {
        // Configurar conexión a la base de datos H2 en memoria
        connection = DriverManager.getConnection(
                "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
                "sa",
                "");

        // Crear tablas y datos de prueba
        PreparedStatement createTable = connection.prepareStatement(
                "CREATE TABLE usuario (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "username VARCHAR(45), " +
                        "password VARCHAR(45), " +
                        "nombre VARCHAR(255), " +
                        "apellido VARCHAR(255), " +
                        "identificacion VARCHAR(255), " +
                        "telefono VARCHAR(255), " +
                        "email VARCHAR(255), " +
                        "es_paciente BOOLEAN, " +
                        "es_empleado BOOLEAN)"
        );
        createTable.executeUpdate();

        // Inicializar DAO con la conexión de H2
        usuarioDao = new UsuarioDaoJDBC(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Cerrar la conexión después de cada prueba
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testInsertar() throws SQLException {
        // Crear un objeto Usuario de prueba
        Usuario usuario = new Usuario();
        usuario.setUsername("testuser");
        usuario.setPassword("testpass");
        usuario.setNombre("Test");
        usuario.setApellido("User");
        usuario.setIdentificacion("12345678");
        usuario.setTelefono("1234567890");
        usuario.setEmail("test@example.com");
        usuario.setEsPaciente(true);
        usuario.setEsEmpleado(false);

        // Insertar el usuario en la base de datos
        int registros = usuarioDao.insertar(usuario);

        // Verificar que la inserción fue exitosa
        assertEquals(1, registros);
    }
}


