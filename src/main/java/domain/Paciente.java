/*
 * Esta clase es de tipo DTO
 * Esto hace referencia al patrón de diseño Data Transfer Object
 * Permite realizar las transferencias de datos entre paquetes
 * Garantiza una alta cohesión y bajo acoplamiento
 */
package domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Slf4j
@SuperBuilder

public class Paciente extends Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_paciente")
    private Integer idPaciente;
    @Column(name = "detalle_eps")
    private String detalleEps;
    @Column(name = "fecha_consulta")
    @Temporal(TemporalType.DATE)
    private Date fechaConsulta;
    @OneToMany(mappedBy = "paciente")
    private List<Asignacion> asignacionList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToOne
    private Usuario usuario;
    @OneToOne(mappedBy = "paciente")
    private Historial historial;

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente=" + idPaciente +
                ", detalleEps='" + detalleEps + '\'' +
                ", fechaConsulta=" + fechaConsulta + ",\n" +
                "| Usuario asociado{" + usuario + ",\n" +
                "| Historial clínico{" + historial +
                '}';
    }
}

