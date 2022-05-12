package rvega.bingo.presentacion;

import rvega.bingo.negocio.MensajeApplication;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import rvega.bingo.dominio.TipoModo;
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
    }

    public void actualizarModo() {
        FacesContext
                .getCurrentInstance()
                .addMessage(null, new FacesMessage("Modo Juego Actualizado"));
    }
    
    public void actualizarDimensiones() {
        FacesContext
                .getCurrentInstance()
                .addMessage(null, new FacesMessage("Dimensiones Rifa Actualizada"));
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
        displayController.init();
        rifaController.init();
        pushBean.enviarJuego("");
        mensajeApplication.purgar();
    }

    public void actualizarCartones() {
        FacesContext
                .getCurrentInstance()
                .addMessage(null, new FacesMessage("Cartones Reiniciados"));
        pushBean.enviarUsuario("");
    }

    public void sortearRifa() {
        FacesContext
                .getCurrentInstance()
                .addMessage(null, new FacesMessage("Iniciando Sorteo"));

//        ExecutorService es = Executors.newFixedThreadPool(1); 
//        es.
        long t = 500;
        try {
            for (int i = 0; i < 50; i++) {
                rifaController.sortear();
                pushBean.enviarJuego("");
                Thread.sleep(t);
                t -= 50;
                if (t < 0) {
                    t = 5;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
