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
import java.util.Date;
import java.util.List;


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

    public Paciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Paciente(Integer idUsuario, String detalleEps, Date fechaConsulta, Historial historial) {
        super(idUsuario);
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
        this.historial = historial;
    }

    public Paciente(Integer idUsuario, String detalleEps, Date fechaConsulta, List<Asignacion> asignacionList, Historial historial) {
        super(idUsuario);
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
        this.asignacionList = asignacionList;
        this.historial = historial;
    }

    public Paciente(String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, String detalleEps, Date fechaConsulta) {
        super(username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
    }

    public Paciente(String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, String detalleEps, Date fechaConsulta, List<Asignacion> asignacionList, Historial historial) {
        super(username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
        this.asignacionList = asignacionList;
        this.historial = historial;
    }

    public Paciente(String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, String detalleEps, Date fechaConsulta, List<Asignacion> asignacionList, Usuario usuario, Historial historial) {
        super(username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
        this.asignacionList = asignacionList;
        this.usuario = usuario;
        this.historial = historial;
    }

    public Paciente(int idUsuario, int idPaciente, String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, String detalleEps, Date fechaConsulta) {
        super(idUsuario, username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        this.idPaciente = idPaciente;
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
    }

    public Paciente(Integer idUsuario, String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, Integer idPaciente, String detalleEps, Date fechaConsulta, List<Asignacion> asignacionList, Historial historial, Usuario usuario) {
        super(idUsuario, username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        this.idPaciente = idPaciente;
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
        this.asignacionList = asignacionList;
        this.historial = historial;
        this.usuario = usuario;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDetalleEps() {
        return detalleEps;
    }

    public void setDetalleEps(String detalleEps) {
        this.detalleEps = detalleEps;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public List<Asignacion> getAsignacionList() {
        return asignacionList;
    }

    public void setAsignacionList(List<Asignacion> asignacionList) {
        this.asignacionList = asignacionList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Historial getHistorial() {
        return historial;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaciente != null ? idPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.idPaciente == null && other.idPaciente != null) || (this.idPaciente != null && !this.idPaciente.equals(other.idPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente=" + idPaciente +
                ", detalleEps='" + detalleEps + '\'' +
                ", fechaConsulta=" + fechaConsulta + ",\n" +
                "| Usuario asociado{" + usuario + ",\n" +
                "| Historial clínico{" + historial +
                '}';
    }
}

