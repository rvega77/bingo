package rvega.bingo.presentacion;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import rvega.bingo.socket.PushBean;

/**
 *
 * @author rvega
 */
@Named
@ApplicationScoped
public class AdminController {

    @Inject
    private PushBean pushBean;
    @Inject
    private MensajeApplication mensajeApplication;
    
    public void actualizarTitulo() {
        FacesContext
                .getCurrentInstance()
                .addMessage(null, new FacesMessage("Título Actualizado"));
    }
    
    public void actualizarBloqueo() {
        FacesContext
                .getCurrentInstance()
                .addMessage(null, new FacesMessage("Bloqueo Actualizado"));
    }
    
    public void actualizarTablero() {
        FacesContext
                .getCurrentInstance()
                .addMessage(null, new FacesMessage("Tablero Reiniciado"));
        pushBean.enviarJuego("");
        mensajeApplication.purgar();
    }
    
    public void actualizarCartones() {
        FacesContext
                .getCurrentInstance()
                .addMessage(null, new FacesMessage("Cartones Reiniciados"));
            pushBean.enviarUsuario("");
}
}
