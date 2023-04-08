package rvega.bingo.dominio;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author rodri
 */
@Data
public class BingoMetadata implements Serializable {

    private int cantidadPorLinea = 15;
    private String palabra = "BINGO";

    public char[] getLetras() {
        return palabra.toCharArray();
    }
}
