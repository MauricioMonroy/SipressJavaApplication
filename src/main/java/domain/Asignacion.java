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

@Entity
@NamedQueries({
        @NamedQuery(name = "Asignacion.findAll", query = "SELECT a FROM Asignacion a"),
        @NamedQuery(name = "Asignacion.findByIdAsignacion", query = "SELECT a FROM Asignacion a WHERE a.idAsignacion = :idAsignacion")})
public class Asignacion implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacion")
    private Integer idAsignacion;
    @JoinColumn(name = "id_funcion", referencedColumnName = "id_funcion")
    @ManyToOne
    private Funcion funcion;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @ManyToOne
    private Paciente paciente;
    @JoinColumn(name = "id_servicio", referencedColumnName = "id_servicio")
    @ManyToOne
    private Servicio servicio;

    public Asignacion() {
    }

    public Asignacion(Integer idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public Asignacion(Integer idAsignacion, Funcion funcion, Paciente paciente, Servicio servicio) {
        this.idAsignacion = idAsignacion;
        this.funcion = funcion;
        this.paciente = paciente;
        this.servicio = servicio;
    }

    public Integer getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(Integer idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacion != null ? idAsignacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignacion)) {
            return false;
        }
        Asignacion other = (Asignacion) object;
        return (this.idAsignacion != null || other.idAsignacion == null) && (this.idAsignacion == null || this.idAsignacion.equals(other.idAsignacion));
    }

    @Override
    public String toString() {
        return "Asignacion{" +
                "idAsignacion=" + idAsignacion +
                ", funcion=" + funcion +
                ", paciente=" + paciente +
                ", servicio=" + servicio +
                '}';
    }
}

