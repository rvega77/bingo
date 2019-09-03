package rvega.bingo.presentacion;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author rvega77
 */
@Named
@ApplicationScoped
public class MensajeApplication {
    private String usuario = "...";
    private String mensaje = "Puedes enviar mensaje de tu celcular...";

    public MensajeApplication() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
