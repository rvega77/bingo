package rvega.bingo.dominio;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author rvega77
 */
public class BingoLinea implements Serializable {

    private String letra;
    private Numero c1;
    private Numero c2;
    private Numero c3;
    private Numero c4;
    private Numero c5;
    private Numero c6;
    private Numero c7;
    private Numero c8;
    private Numero c9;
    private Numero c10;
    private Numero c11;
    private Numero c12;
    private Numero c13;
    private Numero c14;
    private Numero c15;

    public BingoLinea(String letra, List<Numero> lst) {
        this.letra = letra;
        int idx = 0;
        c1 = lst.get(idx++);
        c2 = lst.get(idx++);
        c3 = lst.get(idx++);
        c4 = lst.get(idx++);
        c5 = lst.get(idx++);
        c6 = lst.get(idx++);
        c7 = lst.get(idx++);
        c8 = lst.get(idx++);
        c9 = lst.get(idx++);
        c10 = lst.get(idx++);
        c11 = lst.get(idx++);
        c12 = lst.get(idx++);
        c13 = lst.get(idx++);
        c14 = lst.get(idx++);
        c15 = lst.get(idx++);

    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Numero getC1() {
        return c1;
    }

    public void setC1(Numero c1) {
        this.c1 = c1;
    }

    public Numero getC2() {
        return c2;
    }

    public void setC2(Numero c2) {
        this.c2 = c2;
    }

    public Numero getC3() {
        return c3;
    }

    public void setC3(Numero c3) {
        this.c3 = c3;
    }

    public Numero getC4() {
        return c4;
    }

    public void setC4(Numero c4) {
        this.c4 = c4;
    }

    public Numero getC5() {
        return c5;
    }

    public void setC5(Numero c5) {
        this.c5 = c5;
    }

    public Numero getC6() {
        return c6;
    }

    public void setC6(Numero c6) {
        this.c6 = c6;
    }

    public Numero getC7() {
        return c7;
    }

    public void setC7(Numero c7) {
        this.c7 = c7;
    }

    public Numero getC8() {
        return c8;
    }

    public void setC8(Numero c8) {
        this.c8 = c8;
    }

    public Numero getC9() {
        return c9;
    }

    public void setC9(Numero c9) {
        this.c9 = c9;
    }

    public Numero getC10() {
        return c10;
    }

    public void setC10(Numero c10) {
        this.c10 = c10;
    }

    public Numero getC11() {
        return c11;
    }

    public void setC11(Numero c11) {
        this.c11 = c11;
    }

    public Numero getC12() {
        return c12;
    }

    public void setC12(Numero c12) {
        this.c12 = c12;
    }

    public Numero getC13() {
        return c13;
    }

    public void setC13(Numero c13) {
        this.c13 = c13;
    }

    public Numero getC14() {
        return c14;
    }

    public void setC14(Numero c14) {
        this.c14 = c14;
    }

    public Numero getC15() {
        return c15;
    }

    public void setC15(Numero c15) {
        this.c15 = c15;
    }

}
