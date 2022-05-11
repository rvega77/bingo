package rvega.bingo.dominio;

import java.io.Serializable;

/**
 *
 * @author rvega
 */
public class RifaNicho implements Serializable {

    private int idx;
    private Usuario usuario;
    private boolean ganador;

    public RifaNicho(int idx) {
        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isGanador() {
        return ganador;
    }

    public void setGanador(boolean ganador) {
        this.ganador = ganador;
    }

    @Override
    public String toString() {
        return "RifaNicho{" + "idx=" + idx + ", usuario=" + usuario + '}';
    }

}
