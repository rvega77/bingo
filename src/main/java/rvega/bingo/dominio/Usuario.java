package rvega.bingo.dominio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author rvega77
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable {

    @EqualsAndHashCode.Include
    private String id;
    private String nombre;
    private LocalDateTime tiempo;

    public Usuario() {
        id = UUID.randomUUID().toString();
        tiempo = LocalDateTime.now();
    }

}
