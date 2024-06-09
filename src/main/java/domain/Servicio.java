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
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s"),
        @NamedQuery(name = "Servicio.findByIdServicio", query = "SELECT s FROM Servicio s WHERE s.idServicio = :idServicio"),
        @NamedQuery(name = "Servicio.findByNombre", query = "SELECT s FROM Servicio s WHERE s.nombre = :nombre")})
public class Servicio implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_servicio")
    private Integer idServicio;
    private String nombre;
    @OneToMany(mappedBy = "servicio")
    private List<Asignacion> asignacionList;

    public Servicio() {
    }

    public Servicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Servicio(String nombre) {
        this.nombre = nombre;
    }

    public Servicio(Integer idServicio, String nombre) {
        this.idServicio = idServicio;
        this.nombre = nombre;
    }

    public Servicio(Integer idServicio, String nombre, List<Asignacion> asignacionList) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.asignacionList = asignacionList;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Asignacion> getAsignacionList() {
        return asignacionList;
    }

    public void setAsignacionList(List<Asignacion> asignacionList) {
        this.asignacionList = asignacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicio != null ? idServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio other)) {
            return false;
        }
        return (this.idServicio != null || other.idServicio == null) && (this.idServicio == null || this.idServicio.equals(other.idServicio));
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "idServicio=" + idServicio +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

