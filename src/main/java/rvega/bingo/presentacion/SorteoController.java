package rvega.bingo.presentacion;

import java.io.Serializable;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.Data;
import rvega.bingo.dominio.RifaNicho;
import rvega.bingo.dominio.Usuario;
import rvega.bingo.negocio.MensajeApplication;
import rvega.bingo.negocio.Rifa;
import rvega.bingo.socket.PushBean;
import rvega.bingo.util.TombolaApplication;

/**
 *
 * @author rodri
 */
@Named
@ViewScoped
@Data
public class SorteoController implements Serializable {

    private static final Logger LOG = Logger.getLogger(SorteoController.class.getName());

    @Inject
    private MensajeApplication mensajeApplication;
    @Inject
    private TombolaApplication tombolaApplication;
    @Inject
    private PushBean pushBean;
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

    public String getFmtNumerosSorteados() {
        return String.join(" :: ",
                tombolaApplication.getListaUtilizados()
                        .stream()
                        .map(e -> e.toString())
                        .collect(Collectors.toList())
        );
    }

    public void sortearRifa() {
        rifa.marcarUtilizados(tombolaApplication.getListaUtilizados());
        long t = 500;
        try {
            for (int i = 0; i < 50; i++) {
                int n = tombolaApplication.sacarNumero();
                rifa.marcarPosibleGanador(n);
                pushBean.enviarJuego("");
                tombolaApplication.devolverNumero(n);
                Thread.sleep(t);
                t -= 50;
                if (t < 0) {
                    t = 25;
                }
            }
            // el que importa....
            int g = tombolaApplication.sacarNumero();
            rifa.marcarGanador(g);
            pushBean.enviarJuego("");
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            LOG.log(Level.SEVERE, "SORTEO", ex);
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
