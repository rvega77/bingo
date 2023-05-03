package rvega.bingo.presentacion;

import java.util.logging.Logger;
import rvega.bingo.negocio.MensajeApplication;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import rvega.bingo.dominio.RifaNicho;
import rvega.bingo.negocio.Rifa;
import rvega.bingo.dominio.Usuario;
import rvega.bingo.util.ConfiguracionApplication;
import rvega.bingo.util.TombolaApplication;

/**
 * permite crear una rifa
 *
 * @author rvega
 */
@Named
@ApplicationScoped
@Data
public class RifaController {

    private static final Logger LOG = Logger.getLogger(RifaController.class.getName());

    @Inject
    private ConfiguracionApplication cnf;
    @Inject
    private TombolaApplication tombolaApplication;
    @Inject
    private Rifa rifa;
    @Inject
    private MensajeApplication mensajeApplication;

    // filas y columnas
    private List<Integer> filas;
    private List<Integer> columnas;

    @PostConstruct
    public void init() {
        rifa.init();
        rifa.crearNichos(cnf.getRifa().getMaxNichos());
        tombolaApplication.inicializar(cnf.getRifa().getMaxNichos());

        filas = new ArrayList<>();
        for (int i = 0; i < cnf.getRifa().getFilas(); i++) {
            filas.add(i);
        }
        columnas = new ArrayList<>();
        for (int i = 0; i < cnf.getRifa().getColumnas(); i++) {
            columnas.add(i);
        }
    }

    public RifaNicho get(int f, int c) {
        int posicion = f * cnf.getRifa().getColumnas() + (c + 1);
        return rifa.getMap().get(posicion);
    }

    public String css(int f, int c) {
        RifaNicho n = get(f, c);
        String css = "noseleccionado";
        if (n.isGanador()) {
            css = "ganador";
        } else if (n.isUtilizado()) {
            css = "utilizado";
        } else if (n.getUsuario() != null) {
            css = "seleccionado";
        }

        return "numero-tablero " + css;
    }

    public void adquirir(Usuario usr, int numero) {
        if (cnf.isBloqueado()) {
            throw new IllegalStateException("Juego Bloqueado!!... No se pueden adquirir números.");
        }
        // al redimensionar la rifa, es posible que los cartones tengan un numero mayor al permitido
        if (numero < 1 || numero > cnf.getRifa().getMaxNichos()) {
            throw new IllegalStateException("Número no válido, favor seleccione otro : " + numero);
        }
        Integer actual = rifa.getPosicion(usr);
        rifa.adquirir(numero, usr);
        if (actual == null) {
            mensajeApplication.enviarMensajeSistema("Se ha adquirido el # " + numero);
        } else {
            rifa.liberar(actual, usr);
            mensajeApplication.enviarMensajeSistema("Se ha movido el # " + actual + " » # " + numero);
        }

    }

}
