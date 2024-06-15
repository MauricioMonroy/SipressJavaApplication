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
import java.util.Date;

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaActualizacion;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @OneToOne
    private Paciente paciente;


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

