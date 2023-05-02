package rvega.bingo.presentacion;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;
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
public class AdquirirRifaController implements Serializable {

    private static final Logger LOG = Logger.getLogger(AdquirirRifaController.class.getName());

    @Inject
    private MensajeApplication mensajeApplication;
    @Inject
    private PushBean pushBean;
    @Inject
    private Rifa rifa;

    // especial compra manual
    private int numero = 1;
    private String nombre;

    public void adquirirManual() {
        Usuario usr = new Usuario();
        usr.setNombre(nombre);
        try {
            rifa.adquirir(numero, usr);
            mensajeApplication.enviarMensajeSistema("Se ha adquirido el # " + numero);
            pushBean.enviarJuego("");

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK, número Adquirido !!", null));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "adquirirManual", ex);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));

        }
    }
}
