package rvega.bingo.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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

    private List<RifaNicho> nichos;
    private Map<Integer, RifaNicho> map;
    private int maxNichos;
    private Random rnd;
    //numeros sorteados
    private int ultimoIndiceSorteado;
    private List<Integer> lstNumeroSorteados;
    private RifaNicho ganador;

    @PostConstruct
    public void init() {
        rnd = new Random();
        crearNichos(0);
        ultimoIndiceSorteado = 0;
        lstNumeroSorteados = new ArrayList<>();
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

    public List<Usuario> getUsuarios() {
        return nichos
                .stream()
                .filter(n -> n.getUsuario() != null)
                .map(n -> n.getUsuario())
                .collect(Collectors.toList());
    }

    public Integer getPosicion(Usuario usr) {
        RifaNicho nicho = nichos.stream()
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
        return nichos
                .stream()
                .filter(n -> n.getUsuario() != null)
                .count();
    }

    public RifaNicho getNichoGanador() {
        return ganador;
    }

    public void sortear() {
        // ninguno es ganador
        nichos.forEach(n -> n.setGanador(false));
        int idx = rnd.nextInt(maxNichos);
        // marcar el ganador
        nichos.get(idx).setGanador(true);
        ultimoIndiceSorteado = idx;
    }

    public void marcarUtilizados() {
        lstNumeroSorteados.forEach(n -> {
            nichos.get(n).setUtilizado(true);
        });
    }

    public void marcarGanador() {
        lstNumeroSorteados.add(ultimoIndiceSorteado);
        ganador = nichos.get(ultimoIndiceSorteado);
    }
}
