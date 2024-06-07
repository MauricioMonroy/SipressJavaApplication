/*
 * Esta clase es de tipo DTO
 * Esto hace referencia al patrón de diseño Data Transfer Object
 * Permite realizar las transferencias de datos entre paquetes
 * Garantiza una alta cohesión y bajo acoplamiento
 */
package domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
        @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
        @NamedQuery(name = "Empleado.findByIdEmpleado", query = "SELECT e FROM Empleado e WHERE e.idEmpleado = :idEmpleado"),
        @NamedQuery(name = "Empleado.findByCargo", query = "SELECT e FROM Empleado e WHERE e.cargo = :cargo")})
public class Empleado implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empleado")
    private Integer idEmpleado;
    private String cargo;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona persona;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "empleado")
    private List<Funcion> funcionList;
    @OneToMany(mappedBy = "empleado")
    private List<Perfil> perfilList;

    public Empleado() {
    }

    public Empleado(String cargo) {
        this.cargo = cargo;
    }

    public Empleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Funcion> getFuncionList() {
        return funcionList;
    }

    public void setFuncionList(List<Funcion> funcionList) {
        this.funcionList = funcionList;
    }

    public List<Perfil> getPerfilList() {
        return perfilList;
    }

    public void setPerfilList(List<Perfil> perfilList) {
        this.perfilList = perfilList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado other)) {
            return false;
        }
        return (this.idEmpleado != null || other.idEmpleado == null) && (this.idEmpleado == null || this.idEmpleado.equals(other.idEmpleado));
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", cargo='" + cargo + '\'' +
                ", persona=" + persona +
                ", usuario=" + usuario +
                '}';
    }
}

