/*
 * Esta clase es de tipo DTO
 * Esto hace referencia al patrón de diseño Data Transfer Object
 * Permite realizar las transferencias de datos entre paquetes
 * Garantiza una alta cohesión y bajo acoplamiento
 */
package domain;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
        @NamedQuery(name = "Paciente.findByIdPaciente", query = "SELECT p FROM Paciente p WHERE p.idPaciente = :idPaciente"),
        @NamedQuery(name = "Paciente.findByDetalleEps", query = "SELECT p FROM Paciente p WHERE p.detalleEps = :detalleEps"),
        @NamedQuery(name = "Paciente.findByFechaConsulta", query = "SELECT p FROM Paciente p WHERE p.fechaConsulta = :fechaConsulta")})
public class Paciente implements Serializable {

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
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona persona;
    @OneToOne(mappedBy = "paciente")
    private Historial historial;

    public Paciente() {
    }

    public Paciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Paciente(String detalleEps, Date fechaConsulta) {
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
    }

    public Paciente(Integer idPaciente, String detalleEps, Date fechaConsulta) {
        this.idPaciente = idPaciente;
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
    }

    public Paciente(Integer idPaciente, String detalleEps, Date fechaConsulta, List<Asignacion> asignacionList, Persona persona, Historial historial) {
        this.idPaciente = idPaciente;
        this.detalleEps = detalleEps;
        this.fechaConsulta = fechaConsulta;
        this.asignacionList = asignacionList;
        this.persona = persona;
        this.historial = historial;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
        if (!(object instanceof Paciente other)) {
            return false;
        }
        return (this.idPaciente != null || other.idPaciente == null) && (this.idPaciente == null || this.idPaciente.equals(other.idPaciente));
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente=" + idPaciente +
                ", detalleEps='" + detalleEps + '\'' +
                ", fechaConsulta=" + fechaConsulta +
                ", persona=" + persona +
                ", historial=" + historial +
                '}';
    }
}
