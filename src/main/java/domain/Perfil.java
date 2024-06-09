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
        @NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p"),
        @NamedQuery(name = "Perfil.findByIdPerfil", query = "SELECT p FROM Perfil p WHERE p.idPerfil = :idPerfil"),
        @NamedQuery(name = "Perfil.findByRol", query = "SELECT p FROM Perfil p WHERE p.rol = :rol")})
public class Perfil implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_perfil")
    private Integer idPerfil;
    private String rol;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario usuario;

    public Perfil() {
    }

    public Perfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Perfil(String rol) {
        this.rol = rol;
    }

    public Perfil(Integer idPerfil, String rol) {
        this.idPerfil = idPerfil;
        this.rol = rol;
    }

    public Perfil(Integer idPerfil, String rol, Usuario usuario) {
        this.idPerfil = idPerfil;
        this.rol = rol;
        this.usuario = usuario;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil other)) {
            return false;
        }
        return (this.idPerfil != null || other.idPerfil == null) && (this.idPerfil == null || this.idPerfil.equals(other.idPerfil));
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "idPerfil=" + idPerfil +
                ", rol='" + rol + '\'' + ",\n" +
                "| Usuario asociado{" + usuario +
                '}';
    }
}

