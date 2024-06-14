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
        if (!(object instanceof Funcion other)) {
            return false;
        }
        return (this.idFuncion != null || other.idFuncion == null) && (this.idFuncion == null || this.idFuncion.equals(other.idFuncion));
    }

    @Override
    public String toString() {
        return "Funcion{" +
                "idFuncion=" + idFuncion +
                ", descripcion='" + descripcion + '\'' + ",\n" +
                "| Empleado relacionado{" + empleado +
                '}';
    }
}

