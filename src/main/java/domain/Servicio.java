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
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder

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

    @Override
    public String toString() {
        return "Servicio{" +
                "idServicio=" + idServicio +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

