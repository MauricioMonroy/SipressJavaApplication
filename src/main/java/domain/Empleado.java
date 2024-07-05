/*
 * Esta clase es de tipo DTO
 * Esto hace referencia al patrón de diseño Data Transfer Object
 * Permite realizar las transferencias de datos entre paquetes
 * Garantiza una alta cohesión y bajo acoplamiento
 */
package domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class Empleado extends Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empleado")
    private Integer idEmpleado;
    private String cargo;
    @OneToMany(mappedBy = "empleado")
    private List<Asignacion> asignacionList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "empleado")
    private List<Funcion> funcionList;

    public Empleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleado(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empleado(Integer idUsuario, String cargo) {
        super(idUsuario);
        this.cargo = cargo;
    }

    public Empleado(String cargo, Usuario usuario) {
        this.cargo = cargo;
        this.usuario = usuario;
    }

    public Empleado(int idEmpleado, String cargo, Usuario usuario) {
        this.idEmpleado = idEmpleado;
        this.cargo = cargo;
        this.usuario = usuario;
    }

    public Empleado(String nombre, String apellido, String identificacion, String telefono, String email, Integer idEmpleado) {
        super(nombre, apellido, identificacion, telefono, email);
        this.idEmpleado = idEmpleado;
    }

    public Empleado(String nombre, String apellido, String identificacion, String telefono, String email, String cargo) {
        super(nombre, apellido, identificacion, telefono, email);
        this.cargo = cargo;
    }

    public Empleado(String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, String cargo) {
        super(username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        this.cargo = cargo;
    }

    public Empleado(String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, String cargo, List<Asignacion> asignacionList, Usuario usuario, List<Funcion> funcionList) {
        super(username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        this.cargo = cargo;
        this.asignacionList = asignacionList;
        this.usuario = usuario;
        this.funcionList = funcionList;
    }


    public Empleado(String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, String cargo, List<Funcion> funcionList) {
        super(username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        this.cargo = cargo;
        this.funcionList = funcionList;
    }

    public Empleado(Integer idUsuario, String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, Paciente paciente, Empleado empleado, String cargo) {
        super(idUsuario, username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado, paciente, empleado);
        this.cargo = cargo;
    }

    public Empleado(Integer idUsuario, String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, Paciente paciente, Empleado empleado, String cargo, Usuario usuario) {
        super(idUsuario, username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado, paciente, empleado);
        this.cargo = cargo;
        this.usuario = usuario;
    }

    public Empleado(Integer idUsuario, String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, Paciente paciente, Empleado empleado, String cargo, List<Asignacion> asignacionList, Usuario usuario, List<Funcion> funcionList) {
        super(idUsuario, username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado, paciente, empleado);
        this.cargo = cargo;
        this.asignacionList = asignacionList;
        this.usuario = usuario;
        this.funcionList = funcionList;
    }

    public Empleado(Usuario usuario, String cargo, List<Funcion> funcionList) {
        this.usuario = usuario;
        this.cargo = cargo;
        this.funcionList = funcionList;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", cargo='" + cargo + '\'' + ",\n" +
                ", asignacionList=" + asignacionList +
                ", usuario=" + usuario + ",\n" +
                ", funcionList=" + funcionList + ",\n" +
                '}';
    }
}


