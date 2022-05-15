package rvega.bingo.dominio;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author rvega
 */
@Data
public class Mensaje implements Serializable {

    private String usuario;
    private String texto;
    private String color;
    private long tiempo;

    public Mensaje() {
        tiempo = System.currentTimeMillis();
    }

    public Mensaje(String usuario, String texto) {
        this();
        this.usuario = usuario;
        this.texto = texto;
        this.color = "#ffffff";
    }
    

}
