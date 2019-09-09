package rvega.bingo.dominio;

import java.io.Serializable;

public class Numero implements Serializable {

    private int valor;
    private boolean utilizado;
    private String letra;

    public Numero() {
    }

    public Numero(int valor, String letra) {
        this.valor = valor;
        this.letra = letra;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isUtilizado() {
        return utilizado;
    }

    public void setUtilizado(boolean utilizado) {
        this.utilizado = utilizado;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.valor;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Numero other = (Numero) obj;
        if (this.valor != other.valor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Numero{" + "valor=" + valor + ", utilizado=" + utilizado + ", letra=" + letra + '}';
    }

    public String toFmtString() {
        return letra + " " + valor;
    }

}
