package rvega.bingo.dominio;

import java.io.Serializable;

/**
 *
 * @author rvega77
 */
public class CartonLinea implements Serializable {

    private Numero b;
    private Numero i;
    private Numero n;
    private Numero g;
    private Numero o;

    public CartonLinea() {
        b = new Numero(0, "X");
        i = new Numero(0, "X");
        n = new Numero(0, "X");
        g = new Numero(0, "X");
        o = new Numero(0, "X");
    }

    public Numero getB() {
        return b;
    }

    public void setB(Numero b) {
        this.b = b;
    }

    public Numero getI() {
        return i;
    }

    public void setI(Numero i) {
        this.i = i;
    }

    public Numero getN() {
        return n;
    }

    public void setN(Numero n) {
        this.n = n;
    }

    public Numero getG() {
        return g;
    }

    public void setG(Numero g) {
        this.g = g;
    }

    public Numero getO() {
        return o;
    }

    public void setO(Numero o) {
        this.o = o;
    }

}
