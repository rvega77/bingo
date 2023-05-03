package rvega.bingo.negocio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
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

    private int maxNichos;
    private Map<Integer, RifaNicho> map;
    //ganador
    private RifaNicho ganador;

    @PostConstruct
    public void init() {
        crearNichos(0);
        ganador = null;
    }

    public synchronized void adquirir(int numero, Usuario usr) {
        RifaNicho n = map.get(numero);
        if (n.getUsuario() != null) {
            if (usr.equals(n.getUsuario())) {
                throw new IllegalStateException("Ya es tuyo !!");
            } else {
                throw new IllegalStateException("Ya fue adquirido por : " + n.getUsuario().getNombre());
            }
        }
        n.setUsuario(usr);
    }

    public void liberar(int numero, Usuario usr) {
        map.values().forEach(n -> {
            if (usr.equals(n.getUsuario())) {
                if (n.getPosicion() == numero) {
                    n.setUsuario(null);
                }
            }
        });
    }

    public void crearNichos(int max) {
        maxNichos = max;

        map = new HashMap<>();
        for (int i = 0; i < maxNichos; i++) {
            RifaNicho n = new RifaNicho(i + 1);
            map.put(n.getPosicion(), n);
        }
    }

    public List<Usuario> getUsuarios() {
        return map.values()
                .stream()
                .filter(n -> n.getUsuario() != null)
                .map(n -> n.getUsuario())
                .collect(Collectors.toList());
    }

    public Integer getPosicion(Usuario usr) {
        RifaNicho nicho = map.values().stream()
                .filter(n -> usr.equals(n.getUsuario()))
                .findFirst()
                .orElse(null);
        Integer p = null;
        if (nicho != null) {
            p = nicho.getPosicion();
        }
        return p;
    }

    public long getCantidadUsuarios() {
        return map.values()
                .stream()
                .filter(n -> n.getUsuario() != null)
                .count();
    }

    public RifaNicho getNichoGanador() {
        return ganador;
    }

    public void marcarPosibleGanador(int numero) {
        // ninguno es ganador
        map.values().forEach(n -> n.setGanador(false));
        // marcar el ganador
        map.get(numero).setGanador(true);
    }

    public void marcarUtilizados(List<Integer> lstUtilizados) {
        lstUtilizados.forEach(n -> {
            map.get(n).setUtilizado(true);
        });
    }

    public void marcarGanador(int numero) {
        marcarPosibleGanador(numero);
        ganador = map.get(numero);
    }
}
