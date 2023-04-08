package rvega.bingo.negocio;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;
import lombok.Data;
import rvega.bingo.dominio.Numero;
import rvega.bingo.util.ConfiguracionApplication;

@Named
@ApplicationScoped
@Data
public class Bingo implements Serializable {

    private static final Logger LOG = Logger.getLogger(Bingo.class.getName());

    private static final boolean DEBUG = false;

    @Inject
    private ConfiguracionApplication cnf;
    // bingo
    private Map<Integer, Numero> map;

    @PostConstruct
    public void init() {
        // crear consola de bingo
        map = new HashMap<>();

        int nroLinea = 0;
        for (char letra : cnf.getBingo().getLetras()) {
            String l = Character.toString(letra);
            for (int idx = 1; idx <= cnf.getBingo().getCantidadPorLinea(); idx++) {
                int posicion = idx + (nroLinea * cnf.getBingo().getCantidadPorLinea());
                Numero n = new Numero(posicion, l);
                map.put(n.getValor(), n);
            }
            nroLinea++;
        }

        print();

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
