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

@Entity
@NamedQueries({
        @NamedQuery(name = "Historial.findAll", query = "SELECT h FROM Historial h"),
        @NamedQuery(name = "Historial.findByIdHistorial", query = "SELECT h FROM Historial h WHERE h.idHistorial = :idHistorial"),
        @NamedQuery(name = "Historial.findByMotivoConsulta", query = "SELECT h FROM Historial h WHERE h.motivoConsulta = :motivoConsulta"),
        @NamedQuery(name = "Historial.findByFechaNacimiento", query = "SELECT h FROM Historial h WHERE h.fechaNacimiento = :fechaNacimiento"),
        @NamedQuery(name = "Historial.findBySexo", query = "SELECT h FROM Historial h WHERE h.sexo = :sexo"),
        @NamedQuery(name = "Historial.findByDireccion", query = "SELECT h FROM Historial h WHERE h.direccion = :direccion"),
        @NamedQuery(name = "Historial.findByOcupacion", query = "SELECT h FROM Historial h WHERE h.ocupacion = :ocupacion"),
        @NamedQuery(name = "Historial.findByContactoEmergencia", query = "SELECT h FROM Historial h WHERE h.contactoEmergencia = :contactoEmergencia"),
        @NamedQuery(name = "Historial.findByNombreContactoEmergencia", query = "SELECT h FROM Historial h WHERE h.nombreContactoEmergencia = :nombreContactoEmergencia"),
        @NamedQuery(name = "Historial.findByGrupoSanguineo", query = "SELECT h FROM Historial h WHERE h.grupoSanguineo = :grupoSanguineo"),
        @NamedQuery(name = "Historial.findByUltimaActualizacion", query = "SELECT h FROM Historial h WHERE h.ultimaActualizacion = :ultimaActualizacion")})
public class Historial implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historial")
    private Integer idHistorial;
    @Column(name = "motivo_consulta")
    private String motivoConsulta;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    private String sexo;
    private String direccion;
    private String ocupacion;
    @Column(name = "contacto_emergencia")
    private String contactoEmergencia;
    @Column(name = "nombre_contacto_emergencia")
    private String nombreContactoEmergencia;
    @Lob
    private String alergias;
    @Lob
    @Column(name = "condiciones_preexistentes")
    private String condicionesPreexistentes;
    @Lob
    @Column(name = "medicamentos_actuales")
    private String medicamentosActuales;
    @Lob
    @Column(name = "historial_vacunas")
    private String historialVacunas;
    @Column(name = "grupo_sanguineo")
    private String grupoSanguineo;
    @Lob
    @Column(name = "notas_adicionales")
    private String notasAdicionales;
    @Column(name = "ultima_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaActualizacion;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @OneToOne
    private Paciente paciente;

    public Historial() {
    }

    public Historial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Historial(String motivoConsulta, Date fechaNacimiento, String sexo, String direccion, String ocupacion, String contactoEmergencia, String nombreContactoEmergencia, String alergias, String condicionesPreexistentes, String medicamentosActuales, String historialVacunas, String grupoSanguineo, String notasAdicionales, Date ultimaActualizacion) {
        this.motivoConsulta = motivoConsulta;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.direccion = direccion;
        this.ocupacion = ocupacion;
        this.contactoEmergencia = contactoEmergencia;
        this.nombreContactoEmergencia = nombreContactoEmergencia;
        this.alergias = alergias;
        this.condicionesPreexistentes = condicionesPreexistentes;
        this.medicamentosActuales = medicamentosActuales;
        this.historialVacunas = historialVacunas;
        this.grupoSanguineo = grupoSanguineo;
        this.notasAdicionales = notasAdicionales;
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public Historial(Integer idHistorial, String motivoConsulta, Date fechaNacimiento, String sexo, String direccion, String ocupacion, String contactoEmergencia, String nombreContactoEmergencia, String alergias, String condicionesPreexistentes, String medicamentosActuales, String historialVacunas, String grupoSanguineo, String notasAdicionales, Date ultimaActualizacion) {
        this.idHistorial = idHistorial;
        this.motivoConsulta = motivoConsulta;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.direccion = direccion;
        this.ocupacion = ocupacion;
        this.contactoEmergencia = contactoEmergencia;
        this.nombreContactoEmergencia = nombreContactoEmergencia;
        this.alergias = alergias;
        this.condicionesPreexistentes = condicionesPreexistentes;
        this.medicamentosActuales = medicamentosActuales;
        this.historialVacunas = historialVacunas;
        this.grupoSanguineo = grupoSanguineo;
        this.notasAdicionales = notasAdicionales;
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public Historial(Integer idHistorial, String motivoConsulta, Date fechaNacimiento, String sexo, String direccion, String ocupacion, String contactoEmergencia, String nombreContactoEmergencia, String alergias, String condicionesPreexistentes, String medicamentosActuales, String historialVacunas, String grupoSanguineo, String notasAdicionales, Date ultimaActualizacion, Paciente paciente) {
        this.idHistorial = idHistorial;
        this.motivoConsulta = motivoConsulta;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.direccion = direccion;
        this.ocupacion = ocupacion;
        this.contactoEmergencia = contactoEmergencia;
        this.nombreContactoEmergencia = nombreContactoEmergencia;
        this.alergias = alergias;
        this.condicionesPreexistentes = condicionesPreexistentes;
        this.medicamentosActuales = medicamentosActuales;
        this.historialVacunas = historialVacunas;
        this.grupoSanguineo = grupoSanguineo;
        this.notasAdicionales = notasAdicionales;
        this.ultimaActualizacion = ultimaActualizacion;
        this.paciente = paciente;
    }

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(String contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public String getNombreContactoEmergencia() {
        return nombreContactoEmergencia;
    }

    public void setNombreContactoEmergencia(String nombreContactoEmergencia) {
        this.nombreContactoEmergencia = nombreContactoEmergencia;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getCondicionesPreexistentes() {
        return condicionesPreexistentes;
    }

    public void setCondicionesPreexistentes(String condicionesPreexistentes) {
        this.condicionesPreexistentes = condicionesPreexistentes;
    }

    public String getMedicamentosActuales() {
        return medicamentosActuales;
    }

    public void setMedicamentosActuales(String medicamentosActuales) {
        this.medicamentosActuales = medicamentosActuales;
    }

    public String getHistorialVacunas() {
        return historialVacunas;
    }

    public void setHistorialVacunas(String historialVacunas) {
        this.historialVacunas = historialVacunas;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getNotasAdicionales() {
        return notasAdicionales;
    }

    public void setNotasAdicionales(String notasAdicionales) {
        this.notasAdicionales = notasAdicionales;
    }

    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(Date ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorial != null ? idHistorial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historial other)) {
            return false;
        }
        return (this.idHistorial != null || other.idHistorial == null) && (this.idHistorial == null || this.idHistorial.equals(other.idHistorial));
    }

    @Override
    public String toString() {
        return "Historial{" +
                "idHistorial=" + idHistorial + ",\n" +
                ", motivoConsulta='" + motivoConsulta + '\'' + ",\n" +
                ", fechaNacimiento=" + fechaNacimiento + ",\n" +
                ", sexo='" + sexo + '\'' + ",\n" +
                ", direccion='" + direccion + '\'' + ",\n" +
                ", ocupacion='" + ocupacion + '\'' + ",\n" +
                ", contactoEmergencia='" + contactoEmergencia + '\'' + ",\n" +
                ", nombreContactoEmergencia='" + nombreContactoEmergencia + '\'' + ",\n" +
                ", alergias='" + alergias + '\'' + ",\n" +
                ", condicionesPreexistentes='" + condicionesPreexistentes + '\'' + ",\n" +
                ", medicamentosActuales='" + medicamentosActuales + '\'' + ",\n" +
                ", historialVacunas='" + historialVacunas + '\'' + ",\n" +
                ", grupoSanguineo='" + grupoSanguineo + '\'' + ",\n" +
                ", notasAdicionales='" + notasAdicionales + '\'' + ",\n" +
                ", ultimaActualizacion=" + ultimaActualizacion + ",\n" +
                '}';
    }
}

