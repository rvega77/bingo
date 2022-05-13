package rvega.bingo.presentacion;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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

    @Inject
    private PushBean pushBean;
    @Inject
    private UsuarioSession usuarioSession;
    @Inject
    private RifaController rifaController;

    // numero de rifa
    private int numero;

    @PostConstruct
    public void init() {
        numero = 1;
    }

    public void adquirir() {
        try {
            rifaController.comprar(usuarioSession.getUsuario(), numero);
            pushBean.enviarJuego("");
            FacesContext
                    .getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Número Adquirido", null));
        } catch (Exception ex) {
            FacesContext
                    .getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
        }
    }
}
