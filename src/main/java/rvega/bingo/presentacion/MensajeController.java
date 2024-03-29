package rvega.bingo.presentacion;

import rvega.bingo.negocio.MensajeApplication;
import java.io.Serializable;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import rvega.bingo.dominio.Mensaje;
import rvega.bingo.util.UsuarioSession;

/**
 *
 * @author rvega77
 */
@Named
@RequestScoped
@Data
public class MensajeController implements Serializable {

    @Inject
    private MensajeApplication mensajeApplication;
    @Inject
    private UsuarioSession usuarioSession;

    private String texto;

    public void enviarMensaje() {
        Mensaje m = new Mensaje();

        m.setUsuario(usuarioSession.getUsuario().getNombre());
        m.setFondo(usuarioSession.getColor());
        m.setTexto(texto);
        mensajeApplication.agregar(m);
        texto = null;

        FacesContext
                .getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje Enviado...", null));
    }

    public String enviar() {
        return "/index?faces-redirect=true";
    }

}
