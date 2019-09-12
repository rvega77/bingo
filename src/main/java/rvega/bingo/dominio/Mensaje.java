package rvega.bingo.dominio;

import java.io.Serializable;

/**
 *
 * @author rvega
 */
public class Mensaje implements Serializable {

    private String usuario;
    private String texto;
    private long tiempo;

    public Mensaje() {
        tiempo = System.currentTimeMillis();
    }

    public Mensaje(String usuario, String texto) {
        this();
        this.usuario = usuario;
        this.texto = texto;
    }
    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "usuario=" + usuario + ", texto=" + texto + ", tiempo=" + tiempo + '}';
    }
    

}
