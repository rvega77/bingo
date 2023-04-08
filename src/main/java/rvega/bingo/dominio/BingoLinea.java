package rvega.bingo.dominio;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 *
 * @author rvega77
 */
@Data
public class BingoLinea implements Serializable {

    private String letra;
    private List<Numero> lstNumeros;

    public BingoLinea(String letra, List<Numero> lst) {
        this.letra = letra;
        this.lstNumeros = lst;
    }
}
