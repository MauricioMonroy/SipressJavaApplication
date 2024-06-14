package datos;

import domain.Servicio;

import java.sql.SQLException;
import java.util.List;

public interface ServicioDAO {
    List<Servicio> seleccionar() throws SQLException;

    Servicio seleccionarPorId(int id) throws SQLException;

    int insertar(Servicio servicio) throws SQLException;

    int actualizar(Servicio servicio) throws SQLException;

    int eliminar(Servicio servicio) throws SQLException;
}
