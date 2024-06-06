package domain;

public class Persona {

    // Atributos de la clase que coinciden con los registros de la base de datos
    private int idPersona;
    private String nombre;
    private String apellido;
    private String identificacion;
    private String telefono;
    private String email;

    // Constructor que permite crear objetos de la clase con o sin los atributos especificados
    public Persona() {}

    // Constructor que permite eliminar un objeto de la clase a través de la llave primaria
    public Persona(int idPersona) {
        this.idPersona = idPersona;
    }

    // Constructor que permite insertar un objeto de la clase sin necesidad de la llave primaria
    public Persona(String nombre, String apellido, String identificacion, String telefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.email = email;
    }

    // Constructor que permite la modificación de todos los atributos de un objeto por completo
    public Persona(int idPersona, String nombre, String apellido, String identificacion, String telefono, String email) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.email = email;
    }

    // Métodos Getter y Setter que permiten modificar un atributo específico del objeto
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método ToString para imprimir el estado del objeto en cualquier momento
    @Override
    public String toString() {
        return "Persona{" +
                "idPersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
