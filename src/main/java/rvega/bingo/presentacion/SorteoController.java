package rvega.bingo.presentacion;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rvega.bingo.dominio.RifaNicho;
import rvega.bingo.negocio.Rifa;
import rvega.bingo.socket.PushBean;

/**
 *
 * @author rodri
 */
@Named
@ViewScoped
public class SorteoController implements Serializable {

    @Inject
    private PushBean pushBean;
    @Inject
    private Rifa rifa;

    public String mostrarGanador() {
        String s = "Sin Ganador";
        RifaNicho n = rifa.getNichoGanador();
        if (n != null) {
            if (n.getUsuario() != null) {
                s = n.getPosicion() + " : " + n.getUsuario().getNombre();
            } else {
                s = n.getPosicion() + " : Al Agua...";
            }
        }
        return s;
    }

    public long getCantidadUsuarios() {
        return rifa.getCantidadUsuarios();
    }

    public void sortearRifa() {
        rifa.sortear();
        pushBean.enviarJuego("sorteo");

//        long t = 500;
//        try {
//            for (int i = 0; i < 50; i++) {
//                rifa.sortear();
//                pushBean.enviarJuego("");
//                Thread.sleep(t);
//                t -= 50;
//                if (t < 0) {
//                    t = 5;
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }
}
