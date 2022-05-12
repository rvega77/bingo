package rvega.bingo.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import rvega.bingo.dominio.Numero;

@Named
@ApplicationScoped
public class Bingo implements Serializable {

    private List<Numero> listaB;
    private List<Numero> listaI;
    private List<Numero> listaN;
    private List<Numero> listaG;
    private List<Numero> listaO;
    private Map<Integer, Numero> mapTotal;

    @PostConstruct
    public void init() {
        // crear consola de bingo
        listaB = new ArrayList<>();
        listaI = new ArrayList<>();
        listaN = new ArrayList<>();
        listaG = new ArrayList<>();
        listaO = new ArrayList<>();
        mapTotal = new HashMap<>();

        //letra B
        for (int i = 1; i <= 15; i++) {
            Numero n = new Numero(i, "B");
            listaB.add(n);
            mapTotal.put(i, n);
        }
        //letra I
        for (int i = 16; i <= 30; i++) {
            Numero n = new Numero(i, "I");
            listaI.add(n);
            mapTotal.put(i, n);
        }
        //letra N
        for (int i = 31; i <= 45; i++) {
            Numero n = new Numero(i, "N");
            listaN.add(n);
            mapTotal.put(i, n);
        }
        //letra G
        for (int i = 46; i <= 60; i++) {
            Numero n = new Numero(i, "G");
            listaG.add(n);
            mapTotal.put(i, n);
        }
        //letra O
        for (int i = 61; i <= 75; i++) {
            Numero n = new Numero(i, "O");
            listaO.add(n);
            mapTotal.put(i, n);
        }
    }

    public boolean disponibles() {
        return mapTotal.values().stream().anyMatch((n) -> (!n.isUtilizado()));
    }

    public List<Integer> getListaUtilizados() {
        return mapTotal
                .values()
                .stream()
                .filter(p -> p.isUtilizado())
                .map(Numero::getValor)
                .collect(Collectors.toList());

    }

    public List<Numero> getListaB() {
        return listaB;
    }

    public void setListaB(List<Numero> listaB) {
        this.listaB = listaB;
    }

    public List<Numero> getListaI() {
        return listaI;
    }

    public void setListaI(List<Numero> listaI) {
        this.listaI = listaI;
    }

    public List<Numero> getListaN() {
        return listaN;
    }

    public void setListaN(List<Numero> listaN) {
        this.listaN = listaN;
    }

    public List<Numero> getListaG() {
        return listaG;
    }

    public void setListaG(List<Numero> listaG) {
        this.listaG = listaG;
    }

    public List<Numero> getListaO() {
        return listaO;
    }

    public void setListaO(List<Numero> listaO) {
        this.listaO = listaO;
    }

    public Map<Integer, Numero> getMapTotal() {
        return mapTotal;
    }

    public void setMapTotal(Map<Integer, Numero> mapTotal) {
        this.mapTotal = mapTotal;
    }
}
