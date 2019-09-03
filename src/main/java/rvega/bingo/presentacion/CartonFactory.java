package rvega.bingo.presentacion;

import rvega.bingo.dominio.Carton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import rvega.bingo.dominio.Numero;
import rvega.bingo.dominio.Usuario;

/**
 *
 * @author rvega77
 */
@Named
@ApplicationScoped
public class CartonFactory {

    private final Random rnd = new Random();
    private final Map<Usuario, Carton> mapCarton = Collections.synchronizedMap(new HashMap<>());

    public Carton crear(Usuario usr) {
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
}
