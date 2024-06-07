package domain;

public class UsuarioDTO {

    // Atributos de la clase que coinciden con los registros de la base de datos
    private int idUsuario;
    private String username;
    private String password;

    // Constructor que permite crear objetos de la clase con o sin los atributos especificados
    public UsuarioDTO() {
    }

    // Constructor que permite eliminar un objeto de la clase a través de la llave primaria
    public UsuarioDTO(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Constructor que permite insertar un objeto de la clase sin necesidad de la llave primaria
    public UsuarioDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Constructor que permite la modificación de todos los atributos de un objeto por completo
    public UsuarioDTO(int idUsuario, String username, String password) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
    }

    // Métodos Getter y Setter que permiten modificar un atributo específico del objeto
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "idUsuario=" + idUsuario +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
