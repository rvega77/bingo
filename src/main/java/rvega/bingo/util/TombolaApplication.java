package rvega.bingo.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Representa una tombola con numeros dentro y fuera.
 *
 * @author rodri
 */
@Named
@ApplicationScoped
public class TombolaApplication {

    private static final Logger LOG = Logger.getLogger(TombolaApplication.class.getName());

    private List<Integer> lstNumeros;
    private List<Integer> lstUtilizados;

    public void inicializar(int total) {
        LOG.info("TOMBOLA :: INIT : " + total);

        lstNumeros = new ArrayList<>();
        for (int n = 1; n <= total; n++) {
            lstNumeros.add(n);
        }
        lstUtilizados = new ArrayList<>();
    }

    public int sacarNumero() {
        // sacudir
        Collections.shuffle(lstNumeros);
        // sacar el primero
        int n = lstNumeros.remove(0);
        // mover a la mesita
        lstUtilizados.add(n);
        // informar
        return n;
    }

    public boolean isDisponibles() {
        return !lstNumeros.isEmpty();
    }

    public List<Integer> getListaUtilizados() {
        return Collections.unmodifiableList(lstUtilizados);
    }

}
