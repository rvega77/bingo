package rvega.bingo.dominio;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author rodri
 */
@Data
public class RifaMetadata implements Serializable {

    private int filas = 10;
    private int columnas = 6;

    public int getMaxNichos() {
        return filas * columnas;
    }

    public String toFmtString() {
        return filas + "x" + columnas;
    }
}
