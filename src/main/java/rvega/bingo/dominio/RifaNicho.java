package rvega.bingo.dominio;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author rvega
 */
@Data
public class RifaNicho implements Serializable {

    private int posicion;
    private Usuario usuario;
    private boolean ganador;
    private boolean utilizado;

    public RifaNicho(int idx) {
        this.posicion = idx;
    }
}
