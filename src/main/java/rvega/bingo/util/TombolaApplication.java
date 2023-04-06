package rvega.bingo.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        lstNumeros = new ArrayList<>();
        for (int n = 1; n <= total; n++) {
            lstNumeros.add(n);
        }
        lstUtilizados = new ArrayList<>();
        print("INIT : " + total);
    }

    public int sacarNumero() {
        // sacudir
        Collections.shuffle(lstNumeros);
        // sacar el primero
        int n = lstNumeros.remove(0);
        // mover a la mesita
        lstUtilizados.add(n);
        // informar
        print("<-- " + n);
        return n;
    }

    public void devolverNumero(Integer numero) {
        lstNumeros.add(numero);
        lstUtilizados.remove(numero);
        print("--> " + numero);
    }

    public boolean isDisponibles() {
        return !lstNumeros.isEmpty();
    }

    public List<Integer> getListaUtilizados() {
        return Collections.unmodifiableList(lstUtilizados);
    }

    private void print(String cabecera) {
        LOG.info(
                "\nTOMBOLA :: " + cabecera
                + "\n - DISPONIBLES : " + lstNumeros
                + "\n - UTILIZADOS  : " + lstUtilizados
        );
    }

}
