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

public class Asignacion implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacion")
    private Integer idAsignacion;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleado empleado;
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

    public Asignacion(Integer idAsignacion, Empleado empleado) {
        this.idAsignacion = idAsignacion;
        this.empleado = empleado;
    }

    public Asignacion(Integer idAsignacion, Paciente paciente) {
        this.idAsignacion = idAsignacion;
        this.paciente = paciente;
    }

    public Asignacion(Integer idAsignacion, Servicio servicio) {
        this.idAsignacion = idAsignacion;
        this.servicio = servicio;
    }

    public Asignacion(Empleado empleado, Paciente paciente, Servicio servicio) {
        this.empleado = empleado;
        this.paciente = paciente;
        this.servicio = servicio;
    }

    public Asignacion(Integer idAsignacion, Empleado empleado, Paciente paciente, Servicio servicio) {
        this.idAsignacion = idAsignacion;
        this.empleado = empleado;
        this.paciente = paciente;
        this.servicio = servicio;
    }

    public Integer getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(Integer idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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
        if (!(object instanceof Asignacion other)) {
            return false;
        }
        return (this.idAsignacion != null || other.idAsignacion == null) && (this.idAsignacion == null || this.idAsignacion.equals(other.idAsignacion));
    }

    @Override
    public String toString() {
        return "Asignacion{" +
                "idAsignacion=" + idAsignacion + ",\n" +
                "| Empleado asociado{" + empleado + ",\n" +
                "| Paciente asociado{" + paciente + ",\n" +
                "| Servicio asociado{" + servicio +
                '}' + "\n";
    }
}

