package rvega.bingo.negocio;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import rvega.bingo.dominio.BingoLinea;
import rvega.bingo.dominio.Numero;
import rvega.bingo.util.ConfiguracionApplication;

@Named
@ApplicationScoped
public class Bingo implements Serializable {

    private static final Logger LOG = Logger.getLogger(Bingo.class.getName());

    private static final boolean DEBUG = false;

    @Inject
    private ConfiguracionApplication cnf;
    // bingo
    private Map<Integer, Numero> map;
    private List<BingoLinea> lstBingoLinea;
    private List<Integer> lstColumnas;

    @PostConstruct
    public void init() {
        // crear consola de bingo
        map = new HashMap<>();
        lstBingoLinea = new ArrayList<>();

        int nroLinea = 0;
        for (char letra : cnf.getBingo().getLetras()) {
            List<Numero> lst = new ArrayList<>();
            String l = Character.toString(letra);
            for (int idx = 1; idx <= cnf.getBingo().getCantidadPorLinea(); idx++) {
                int posicion = idx + (nroLinea * cnf.getBingo().getCantidadPorLinea());
                Numero n = new Numero(posicion, l);
                map.put(n.getValor(), n);
                lst.add(n);
            }
            nroLinea++;
            lstBingoLinea.add(new BingoLinea(l, lst));
        }
        lstColumnas = new ArrayList<>();
        for (int i = 1; i <= cnf.getBingo().getCantidadPorLinea(); i++) {
            lstColumnas.add(i);
        }
        print();

    }

    public List<BingoLinea> getLstBingoLinea() {
        return lstBingoLinea;
    }

    public List<Integer> getLstColumnas() {
        return lstColumnas;
    }

    public int getTotalNumeros() {
        return map.size();
    }

    public Numero getNumero(int posicion) {
        return map.get(posicion);
    }

    public void print() {
        if (DEBUG) {
            LOG.info("\nBINGO :: \n" + map.toString());
        }
    }
}
