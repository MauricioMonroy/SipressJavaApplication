/*
 * Esta clase es de tipo DTO
 * Esto hace referencia al patrón de diseño Data Transfer Object
 * Permite realizar las transferencias de datos entre paquetes
 * Garantiza una alta cohesión y bajo acoplamiento
 */
package domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder

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
    private Timestamp ultimaActualizacion;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @OneToOne
    private Paciente paciente;

    public Historial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Historial(String motivoConsulta, Date fechaNacimiento, String sexo, String direccion, String ocupacion,
                     String contactoEmergencia, String nombreContactoEmergencia, String alergias,
                     String condicionesPreexistentes, String medicamentosActuales, String historialVacunas,
                     String grupoSanguineo, String notasAdicionales, Timestamp ultimaActualizacion, Paciente paciente) {
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

    public Historial(int idHistorial, String motivoConsulta, Date fechaNacimiento, String sexo, String direccion,
                     String ocupacion, String contactoEmergencia, String nombreContactoEmergencia, String alergias,
                     String condicionesPreexistentes, String medicamentosActuales, String historialVacunas,
                     String grupoSanguineo, String notasAdicionales, Timestamp ultimaActualizacion, Paciente paciente) {
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
                ", paciente=" + paciente + ",\n" +
                '}';
    }
}

