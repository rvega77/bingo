package rvega.bingo.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import lombok.Data;
import rvega.bingo.dominio.RifaNicho;
import rvega.bingo.dominio.Usuario;

/**
 *
 * @author rvega
 */
@Named
@ApplicationScoped
@Data
public class Rifa {

    private List<RifaNicho> nichos;
    private Map<Integer, RifaNicho> map;
    private int maxNichos;

    @PostConstruct
    public void init() {
    }

    public synchronized void adquirir(int numero, Usuario usr) {
        RifaNicho n = map.get(numero);
        if (n.getUsuario() != null) {
            if (usr.equals(usr)) {
                throw new IllegalStateException("Ya es tuyo !!");
            } else {
                throw new IllegalStateException("Ya fue adquirido por : " + n.getUsuario().getNombre());
            }
        }
        n.setUsuario(usr);
    }

    public void liberar(int numero, Usuario usr) {
        nichos.forEach(n -> {
            if (usr.equals(n.getUsuario())) {
                if (n.getPosicion() != numero) {
                    n.setUsuario(null);
                }
            }
        });
    }

    public void crearNichos(int max) {
        maxNichos = max;

        nichos = new ArrayList<>();
        map = new HashMap<>();
        for (int i = 0; i < maxNichos; i++) {
            RifaNicho n = new RifaNicho(i + 1);
            nichos.add(n);
            map.put(n.getPosicion(), n);
        }
    }

    public void sortear() {
        nichos.forEach(n -> n.setGanador(false));

        Random rnd = new Random();
        nichos.get(rnd.nextInt(maxNichos)).setGanador(true);

    }
}