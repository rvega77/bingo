package rvega.bingo.util;

import rvega.bingo.dominio.Carton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import rvega.bingo.dominio.Numero;
import rvega.bingo.dominio.Usuario;

/**
 *
 * @author rvega77
 */
@Named
@ApplicationScoped
public class CartonFactory {

    @Inject
    private ConfiguracionApplication cnf;

    private final Random rnd = new Random();
    private final Map<Usuario, Carton> mapCarton = Collections.synchronizedMap(new HashMap<>());

    public void reset() {
        mapCarton.clear();
    }

    public Carton crear(Usuario usr) {

        if (cnf.isBloqueado()) {
            // solo se puede crear uno
            if (mapCarton.containsKey(usr)) {
                throw new IllegalStateException("No se pueden crear cartones");
            }
        }

        Carton c = new Carton();
        c.agregar("B", crearColumna("B", 1));
        c.agregar("I", crearColumna("I", 16));
        c.agregar("N", crearColumna("N", 31));
        c.agregar("G", crearColumna("G", 46));
        c.agregar("O", crearColumna("O", 61));

        Numero n = c.getMapColumnas().get("N").remove(2);
        c.getMapNumeros().remove(n.getValor());
        c.getMapColumnas().get("N").add(2, null);

        mapCarton.put(usr, c);
        return c;
    }

    private List<Numero> crearColumna(String letra, int corrimiento) {
        List<Numero> c = new ArrayList<>();
        do {
            int v = rnd.nextInt(15) + corrimiento;
            Numero n = new Numero(v, letra);
            if (!c.contains(n)) {
                c.add(n);
            }
        } while (c.size() < 5);
        return c;
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(mapCarton.keySet());
    }

    public List<Carton> getCartones() {
        return new ArrayList<>(mapCarton.values());
    }

    public Carton getCarton(Usuario u) {
        return mapCarton.get(u);
    }

    public boolean existeUsuario(Usuario u) {
        return mapCarton.containsKey(u);
    }

    public Integer getCantidadUsuarios() {
        return mapCarton.size();
    }
}
