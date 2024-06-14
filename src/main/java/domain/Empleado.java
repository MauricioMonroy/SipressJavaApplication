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
import java.util.List;

public class Empleado extends Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empleado")
    private Integer idEmpleado;
    private String cargo;
    @OneToMany(mappedBy = "empleado")
    private List<Asignacion> asignacionList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "empleado")
    private List<Funcion> funcionList;

    public Empleado() {
    }

    public Empleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleado(Integer idUsuario, String cargo) {
        super(idUsuario);
        this.cargo = cargo;
    }

    public Empleado(Integer idUsuario, String cargo, List<Asignacion> asignacionList, List<Funcion> funcionList) {
        super(idUsuario);
        this.cargo = cargo;
        this.asignacionList = asignacionList;
        this.funcionList = funcionList;
    }

    public Empleado(Integer idUsuario, String cargo, List<Asignacion> asignacionList, Usuario usuario, List<Funcion> funcionList) {
        super(idUsuario);
        this.cargo = cargo;
        this.asignacionList = asignacionList;
        this.usuario = usuario;
        this.funcionList = funcionList;
    }

    public Empleado(String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, String cargo) {
        super(username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        this.cargo = cargo;
    }

    public Empleado(String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, String cargo, Usuario usuario) {
        super(username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        this.cargo = cargo;
        this.usuario = usuario;
    }

    public Empleado(Integer idUsuario, String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, Integer idEmpleado, String cargo) {
        super(idUsuario, username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        this.idEmpleado = idEmpleado;
        this.cargo = cargo;
    }

    public Empleado(Integer idUsuario, String username, String password, String nombre, String apellido, String identificacion, String telefono, String email, Boolean esPaciente, Boolean esEmpleado, Integer idEmpleado, String cargo, List<Asignacion> asignacionList, Usuario usuario, List<Funcion> funcionList) {
        super(idUsuario, username, password, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
        this.idEmpleado = idEmpleado;
        this.cargo = cargo;
        this.asignacionList = asignacionList;
        this.usuario = usuario;
        this.funcionList = funcionList;
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

    public List<Funcion> getFuncionList() {
        return funcionList;
    }

    public void setFuncionList(List<Funcion> funcionList) {
        this.funcionList = funcionList;
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
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", cargo='" + cargo + '\'' + ",\n" +
                "| Usuario asociado{" + usuario +
                '}';
    }
}


