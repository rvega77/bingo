package rvega.bingo.dominio;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rvega77
 */
public class Carton implements Serializable {

    private Map<Integer, Numero> mapNumeros;
    private Map<String, List<Numero>> mapColumnas;

    public Carton() {
        mapNumeros = new HashMap<>();
        mapColumnas = new HashMap<>();
    }

    public void agregar(String letra, List<Numero> lst) {
        mapColumnas.put(letra, lst);
        lst.forEach(n -> mapNumeros.put(n.getValor(), n));
    }

    public Map<Integer, Numero> getMapNumeros() {
        return mapNumeros;
    }

    public void setMapNumeros(Map<Integer, Numero> mapNumeros) {
        this.mapNumeros = mapNumeros;
    }

    public Map<String, List<Numero>> getMapColumnas() {
        return mapColumnas;
    }

    public void setMapColumnas(Map<String, List<Numero>> mapColumnas) {
        this.mapColumnas = mapColumnas;
    }

    public void conmutar(int numero) {
        boolean v = !mapNumeros.get(numero).isUtilizado();
        mapNumeros.get(numero).setUtilizado(v);

    }

    public Integer contarFaltantes(List<Integer> lstNumeros) {
        int total = 24;
        for (Numero n : mapNumeros.values()) {
            if (lstNumeros.contains(n.getValor())) {
                total--;
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "Carton{" + "mapColumnas=" + mapColumnas + '}';
    }

}
