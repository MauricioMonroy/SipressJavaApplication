package datos;

import domain.Asignacion;

import java.sql.SQLException;
import java.util.List;

public interface AsignacionDAO {
    List<Asignacion> seleccionar() throws SQLException;

    Asignacion seleccionarPorId(int id) throws SQLException;

    int insertar(Asignacion asignacion) throws SQLException;

    int actualizar(Asignacion asignacion) throws SQLException;

    int eliminar(Asignacion asignacion) throws SQLException;
}
