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

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class Paciente extends Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_paciente")
    private Integer idPaciente;
    @Column(name = "detalle_eps")
    private String detalleEps;
    @Column(name = "fecha_consulta")
    @Temporal(TemporalType.DATE)
    private Date fechaConsulta;
    @OneToMany(mappedBy = "paciente")
    private List<Asignacion> asignacionList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToOne
    private Usuario usuario;
    @OneToOne(mappedBy = "paciente")
    private Historial historial;

    public Paciente() {
    }

    public Paciente(Usuario usuario) {
        this.usuario = usuario;
    }

    public Paciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Paciente(Integer idUsuario) {
        super(idUsuario);
    }

    public Paciente(String detalleEps, Usuario usuario) {
        this.detalleEps = detalleEps;
        this.usuario = usuario;
    }

    public Paciente(String detalleEps, Date fechaConsulta, Usuario usuario) {
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
        this.usuario = usuario;
    }

    public Paciente(Integer idPaciente, String detalleEps, Date fechaConsulta) {
        this.idPaciente = idPaciente;
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
    }

    public Paciente(int idPaciente, String detalleEps, Date fechaConsulta, Usuario usuario) {
        super(usuario.getNombre(), usuario.getApellido(), usuario.getIdentificacion(), usuario.getTelefono(), usuario.getEmail());
        this.idPaciente = idPaciente;
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
    }

    public Paciente(String nombre, String apellido, String identificacion, String telefono, String email, Integer idPaciente, String detalleEps, Date fechaConsulta) {
        super(nombre, apellido, identificacion, telefono, email);
        this.idPaciente = idPaciente;
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
    }

    public Paciente(String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, String detalleEps, Date fechaConsulta) {
        super(username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
    }

    public Paciente(String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, String detalleEps, Date fechaConsulta, Usuario usuario) {
        super(username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
        this.usuario = usuario;
    }

    public Paciente(Integer idUsuario, String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, Paciente paciente, Empleado empleado, String detalleEps, Date fechaConsulta, Usuario usuario) {
        super(idUsuario, username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado, paciente, empleado);
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
        this.usuario = usuario;
    }

    public Paciente(Integer idUsuario, String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, Paciente paciente, Empleado empleado, String detalleEps, Date fechaConsulta) {
        super(idUsuario, username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado, paciente, empleado);
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
    }

    public Paciente(String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, String detalleEps, Date fechaConsulta, Historial historial) {
        super(username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
        this.historial = historial;
    }

    public Paciente(Integer idUsuario, String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, Paciente paciente, Empleado empleado, String detalleEps, Date fechaConsulta, Historial historial) {
        super(idUsuario, username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado, paciente, empleado);
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
        this.historial = historial;
    }

    public Paciente(Usuario usuario, String detalleEps, Date fechaConsulta, Historial historial) {
        this.usuario = usuario;
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
        this.historial = historial;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente=" + idPaciente +
                ", detalleEps='" + detalleEps + '\'' +
                ", fechaConsulta=" + fechaConsulta + ",\n" +
                "| Usuario asociado{" + usuario + ",\n" +
                '}';
    }
}

