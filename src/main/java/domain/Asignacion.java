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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder

public class Asignacion implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacion")
    private Integer idAsignacion;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleado empleado;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @ManyToOne
    private Paciente paciente;
    @JoinColumn(name = "id_servicio", referencedColumnName = "id_servicio")
    @ManyToOne
    private Servicio servicio;

    @Override
    public String toString() {
        return "Asignacion{" +
                "idAsignacion=" + idAsignacion + ",\n" +
                '}';
    }
}

