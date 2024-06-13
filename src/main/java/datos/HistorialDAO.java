package datos;

import domain.Historial;

import java.sql.SQLException;
import java.util.List;

public interface HistorialDAO {
    List<Historial> seleccionar() throws SQLException;

    Historial seleccionarPorId(int id) throws SQLException;

    int insertar(Historial historial) throws SQLException;

    int actualizar(Historial historial) throws SQLException;

    int eliminar(Historial historial) throws SQLException;
}
