package rvega.bingo.presentacion;

import rvega.bingo.negocio.MensajeApplication;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import rvega.bingo.dominio.TipoModo;
import rvega.bingo.socket.PushBean;
import rvega.bingo.util.ConfiguracionApplication;

/**
 *
 * @author rvega
 */
@Named
@ApplicationScoped
public class AdminController {

    @Inject
    private ConfiguracionApplication cnf;
    @Inject
    private PushBean pushBean;
    @Inject
    private DisplayController displayController;
    @Inject
    private RifaController rifaController;
    @Inject
    private MensajeApplication mensajeApplication;

    public TipoModo[] getLstModo() {
        return TipoModo.values();
    }

    public void actualizarTitulo() {
        FacesContext
                .getCurrentInstance()
                .addMessage(null, new FacesMessage("Título Actualizado"));
        pushBean.enviarJuego("");
    }

    public void actualizarModo() {
        FacesContext
                .getCurrentInstance()
                .addMessage(null, new FacesMessage("Modo Juego Actualizado"));
        reinicarTableros();
        pushBean.enviarCarton("");
    }

    public void actualizarDimensiones() {
        FacesContext
                .getCurrentInstance()
                .addMessage(null, new FacesMessage("Dimensiones Rifa Actualizada"));

        reinicarTableros();
        pushBean.enviarCarton("");
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

        reinicarTableros();
        mensajeApplication.purgar();
    }

    public void actualizarCartones() {
        FacesContext
                .getCurrentInstance()
                .addMessage(null, new FacesMessage("Cartones Reiniciados"));
        pushBean.enviarUsuario("");
    }

    private void reinicarTableros() {
        if (cnf.isModoBingo()) {
            displayController.init();
        }
        if (cnf.isModoRifa()) {
            rifaController.init();
        }
        pushBean.enviarJuego("");
    }

}
