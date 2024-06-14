/*
 * Esta clase es de tipo DTO
 * Esto hace referencia al patrón de diseño Data Transfer Object
 * Permite realizar las transferencias de datos entre paquetes
 * Garantiza una alta cohesión y bajo acoplamiento
 */
package domain;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String identificacion;
    private String telefono;
    private String email;
    @Column(name = "es_paciente")
    private Boolean esPaciente;
    @Column(name = "es_empleado")
    private Boolean esEmpleado;
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Paciente paciente;
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Empleado empleado;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.email = email;
        this.esPaciente = esPaciente;
        this.esEmpleado = esEmpleado;
    }

    public Usuario(Integer idUsuario, String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.email = email;
        this.esPaciente = esPaciente;
        this.esEmpleado = esEmpleado;
    }

    public Usuario(Integer idUsuario, String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, Empleado empleado, Paciente paciente) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.email = email;
        this.esPaciente = esPaciente;
        this.esEmpleado = esEmpleado;
        this.empleado = empleado;
        this.paciente = paciente;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
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

    public Boolean isEsPaciente() {
        return esPaciente;
    }

    public void setEsPaciente(Boolean esPaciente) {
        this.esPaciente = esPaciente;
    }

    public Boolean isEsEmpleado() {
        return esEmpleado;
    }

    public void setEsEmpleado(Boolean esEmpleado) {
        this.esEmpleado = esEmpleado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario other)) {
            return false;
        }
        return (this.idUsuario != null || other.idUsuario == null) && (this.idUsuario == null || this.idUsuario.equals(other.idUsuario));
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", esPaciente=" + esPaciente +
                ", esEmpleado=" + esEmpleado + ",\n" +
                ", paciente=" + paciente + ",\n" +
                ", empleado=" + empleado +
                '}';
    }
}
