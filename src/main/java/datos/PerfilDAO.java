package datos;

import domain.Perfil;

import java.sql.SQLException;
import java.util.List;

public interface PerfilDAO {
    List<Perfil> seleccionar() throws SQLException;

    Perfil seleccionarPorId(int id) throws SQLException;

    int insertar(Perfil perfil) throws SQLException;

    int actualizar(Perfil perfil) throws SQLException;

    int eliminar(Perfil perfil) throws SQLException;
}
