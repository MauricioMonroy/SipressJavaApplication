/*
 * Esta clase es de tipo DTO
 * Esto hace referencia al patrón de diseño Data Transfer Object
 * Permite realizar las transferencias de datos entre paquetes
 * Garantiza una alta cohesión y bajo acoplamiento
 */
package domain;

import java.io.Serial;
import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "Funcion.findAll", query = "SELECT f FROM Funcion f"),
        @NamedQuery(name = "Funcion.findByIdFuncion", query = "SELECT f FROM Funcion f WHERE f.idFuncion = :idFuncion"),
        @NamedQuery(name = "Funcion.findByDescripcion", query = "SELECT f FROM Funcion f WHERE f.descripcion = :descripcion")})
public class Funcion implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_funcion")
    private Integer idFuncion;
    private String descripcion;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleado empleado;

    public Funcion() {
    }

    public Funcion(Integer idFuncion) {
        this.idFuncion = idFuncion;
    }

    public Funcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Funcion(String descripcion, Empleado empleado) {
        this.descripcion = descripcion;
        this.empleado = empleado;
    }

    public Funcion(Integer idFuncion, String descripcion, Empleado empleado) {
        this.idFuncion = idFuncion;
        this.descripcion = descripcion;
        this.empleado = empleado;
    }

    public Integer getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(Integer idFuncion) {
        this.idFuncion = idFuncion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idFuncion != null ? idFuncion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcion)) {
            return false;
        }
        Funcion other = (Funcion) object;
        if ((this.idFuncion == null && other.idFuncion != null) || (this.idFuncion != null && !this.idFuncion.equals(other.idFuncion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Funcion{" +
                "idFuncion=" + idFuncion +
                ", descripcion='" + descripcion + '\'' +
                "| Empleado relacionado{" + empleado +
                '}';
    }
}

