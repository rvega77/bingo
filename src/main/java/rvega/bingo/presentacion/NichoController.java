package rvega.bingo.presentacion;

import java.io.Serializable;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;
import rvega.bingo.socket.PushBean;
import rvega.bingo.util.UsuarioSession;

/**
 *
 * @author rodri
 */
@Named
@SessionScoped
@Data
public class NichoController implements Serializable {

    private static final Logger LOG = Logger.getLogger(NichoController.class.getName());

    @Inject
    private PushBean pushBean;
    @Inject
    private UsuarioSession usuarioSession;
    @Inject
    private RifaController rifaController;

    // numero de rifa
    private int numero;
    private Integer adquirido;

    @PostConstruct
    public void init() {
        numero = 1;
        adquirido = null;
    }

    public void adquirir() {
        try {
            rifaController.adquirir(usuarioSession.getUsuario(), numero);
            adquirido = numero;
            pushBean.enviarJuego("");
            FacesContext
                    .getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Número Adquirido", null));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "adquirir", ex);
            FacesContext
                    .getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
        }
    }
}
