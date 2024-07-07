/*
 * Esta clase es de tipo DTO
 * Esto hace referencia al patrón de diseño Data Transfer Object
 * Permite realizar las transferencias de datos entre paquetes
 * Garantiza una alta cohesión y bajo acoplamiento
 */
package domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)

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

    public Usuario(String nombre, String apellido, String identificacion, String telefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.email = email;
    }

    public Usuario(Integer idUsuario, String nombre, String apellido, String identificacion, String telefono, String email) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.email = email;
    }

    public Usuario(String username, String password, String nombre, String apellido,
                   String identificacion, String telefono, String email,
                   Boolean esPaciente, Boolean esEmpleado) {
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

    public Usuario(Integer idUsuario, String username, String password, String nombre,
                   String apellido, String identificacion, String telefono, String email,
                   Boolean esPaciente, Boolean esEmpleado) {
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

    public Usuario(Integer idUsuario, String username, String nombre, String apellido,
                   String identificacion, String telefono, String email,
                   Boolean esPaciente, Boolean esEmpleado) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.email = email;
        this.esPaciente = esPaciente;
        this.esEmpleado = esEmpleado;
    }

    public Usuario(String nombre, String apellido, String identificacion, String telefono,
                   String email, boolean b, boolean b1) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.email = email;
        this.esPaciente = b;
        this.esEmpleado = b1;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' + ",\n" +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' + ",\n" +
                ", esPaciente=" + esPaciente +
                ", esEmpleado=" + esEmpleado + ",\n" +
                '}';
    }
}
