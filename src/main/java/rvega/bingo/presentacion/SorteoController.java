package rvega.bingo.presentacion;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import rvega.bingo.dominio.RifaNicho;
import rvega.bingo.dominio.Usuario;
import rvega.bingo.negocio.MensajeApplication;
import rvega.bingo.negocio.Rifa;
import rvega.bingo.socket.PushBean;

/**
 *
 * @author rodri
 */
@Named
@ViewScoped
@Data
public class SorteoController implements Serializable {

    @Inject
    private PushBean pushBean;
    @Inject
    private MensajeApplication mensajeApplication;
    @Inject
    private Rifa rifa;

    // especial compra manual
    private int numero = 1;
    private String nombre;

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
//        rifa.sortear();
//        pushBean.enviarJuego("sorteo");

        long t = 500;
        try {
            for (int i = 0; i < 50; i++) {
                rifa.sortear();
                pushBean.enviarJuego("");
                Thread.sleep(t);
                t -= 50;
                if (t < 0) {
                    t = 25;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void adquirirManual() {
        Usuario usr = new Usuario();
        usr.setNombre(nombre);
        try {
            rifa.adquirir(numero, usr);
            mensajeApplication.enviarMensajeSistema(nombre + " tiene el # " + numero);
            pushBean.enviarJuego("");

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK, número Adquirido !!", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));

        }
    }
}
