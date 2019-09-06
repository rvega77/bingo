package rvega.bingo.presentacion;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author rvega
 */
@Named
@ApplicationScoped
public class AdminController {

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
    }

    public void actualizarCartones() {
        FacesContext
                .getCurrentInstance()
                .addMessage(null, new FacesMessage("Cartones Reiniciados"));
    }
}
