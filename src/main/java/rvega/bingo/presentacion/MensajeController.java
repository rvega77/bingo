package rvega.bingo.presentacion;

import rvega.bingo.presentacion.CartonController;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rvega77
 */
@Named
@SessionScoped
public class MensajeController implements Serializable {

    @Inject
    private MensajeApplication mensajeApplication;
    @Inject
    private CartonController cartonController;

    private String texto;

    public String enviar() {
        mensajeApplication.setUsuario(cartonController.getNombre());
        mensajeApplication.setMensaje(texto);
        texto = null;
        return "/carton.xhtml?faces-redirect=true";
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}
