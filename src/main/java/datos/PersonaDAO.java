package datos;

import domain.Persona;

import java.sql.SQLException;
import java.util.List;

public interface PersonaDAO {
    List<Persona> seleccionar() throws SQLException;

    Persona seleccionarPorId(int id) throws SQLException;

    int insertar(Persona persona) throws SQLException;

    int actualizar(Persona persona) throws SQLException;

    int eliminar(Persona persona) throws SQLException;
}
